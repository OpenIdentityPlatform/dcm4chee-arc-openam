import org.forgerock.util.Function;
import org.forgerock.util.promise.NeverThrowsException;
import org.forgerock.util.promise.Promise;
import org.forgerock.http.protocol.Request;
import org.forgerock.http.protocol.Response;
import org.forgerock.http.protocol.ResponseException;
import org.forgerock.http.routing.UriRouterContext;
import org.forgerock.http.header.LocationHeader;
import org.forgerock.openig.el.Bindings;

import static java.lang.String.format;
import static org.forgerock.http.protocol.Responses.newInternalServerError;
import static org.forgerock.openig.el.Bindings.bindings;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.forgerock.http.MutableUri;

next.handle(context, request).then(new Function<Response, Response, NeverThrowsException>() {
                       @Override
                       public Response apply(final Response value) {
                           UriRouterContext routerContext = context.asContext(UriRouterContext.class);
                           return processResponse(value, bindings(context, request, value),
                                                  routerContext.getOriginalUri());
                       }
                   })
def Response processResponse(Response response, Bindings bindings, final URI originalUri) {
    LocationHeader header = LocationHeader.valueOf(response);
    if (header.getLocationUri() != null) {
        try {
            URI currentURI = new URI(header.getLocationUri());
            if(currentURI.getRawFragment() != null) {
                MutableUri mutableUri = MutableUri.uri(header.getLocationUri());
                Pattern pat = Pattern.compile("([^&=]+)=([^&]*)");
                Matcher matcher = pat.matcher(mutableUri.getRawFragment());
                Map<String, String> map = new HashMap<String, String>();
                while (matcher.find()) {
                    map.put(matcher.group(1), matcher.group(2));
                }
                System.out.println(map);
                
                String rawFragment = String.format("state=%s&session_state=%s&code=%s", map.get("state"), map.get("session_state"), map.get("code"));
                mutableUri.setRawFragment(rawFragment);;
                System.err.println(mutableUri.toString());
                response.getHeaders().put(LocationHeader.NAME, mutableUri.toString());
            }
        } catch (URISyntaxException | ResponseException ex) {
            logger.error("Cannot rebase 'Location' URI", ex);
            return newInternalServerError(ex);
        }
    }

    return response;
}