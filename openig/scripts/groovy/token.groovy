import java.util.regex.Matcher;
import java.util.regex.Pattern;

String entity = request.entity = request.entity.toString()
Pattern p = Pattern.compile("redirect_uri=[^\\&]+");
Matcher m = p.matcher(entity);
if(m.find()) {
    String redirectUri =  m.group();
    String split = redirectUri.split("%3F")[0]; 		
    System.out.println(split);
    entity = entity.replace(redirectUri, split);
}

request.entity = entity + '&client_secret=11111111'
next.handle(context, request)