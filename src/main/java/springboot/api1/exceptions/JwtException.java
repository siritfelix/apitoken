package springboot.api1.exceptions;

public class JwtException extends UnauthorizedException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final String DESCRIPTION = "Jwt exception";

    public JwtException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
