package com.autentication.authenticationservice.security;


/*
public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    @Autowired
    private MessageSource messages;


    @Autowired
    private HandlerExceptionResolver resolver;

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
System.out.println(authException.getLocalizedMessage());
        System.out.println(authException.getCause());
        System.out.println(authException.getSuppressed());
      //  System.out.println(authException.);
        PrintWriter writer = response.getWriter();
        writer.println("HTTP Status 401 : " + authException.getMessage());

resolver.resolveException(request,response,null,authException);
        //super.onAuthenticationFailure(request, response, exception);



       // String errorMessage = messages.getMessage("message.badCredentials", null, locale);

        if (authException.getMessage()
                .equalsIgnoreCase("User is disabled")) {
        //    errorMessage = messages.getMessage("auth.message.disabled", null, locale);
        } else if (authException.getMessage()
                .equalsIgnoreCase("User account has expired")) {
          //  errorMessage = messages.getMessage("auth.message.expired", null, locale);
        } else if (authException.getMessage()
                .equalsIgnoreCase("blocked")) {
        //    errorMessage = messages.getMessage("auth.message.blocked", null, locale);
        } else if (authException.getMessage()
                .equalsIgnoreCase("unusual location")) {
          //  errorMessage = messages.getMessage("auth.message.unusual.location", null, locale);
        }

        request.getSession()
                .setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, errorMessage);
    }



    @Override
    public void afterPropertiesSet() {
        setRealmName("Fail login");
        super.afterPropertiesSet();
    }

}*/