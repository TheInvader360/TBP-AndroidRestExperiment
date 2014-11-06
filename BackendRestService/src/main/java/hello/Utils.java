package hello;

import javax.servlet.http.HttpServletRequest;

public class Utils {

  public static String getUrl(HttpServletRequest request) {
    String url = request.getRequestURL().toString();
    String queryString = request.getQueryString();
    if (queryString != null) url += "?" + queryString;
    return url;
  }

}
