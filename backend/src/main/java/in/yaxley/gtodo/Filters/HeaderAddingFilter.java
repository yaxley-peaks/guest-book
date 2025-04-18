package in.yaxley.gtodo.Filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
class HeaderAddingFilter extends GenericFilterBean {

    @Value("${by}")
    private String presenter;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        httpServletResponse.setHeader("X-Served-By", "Spring Boot");
        httpServletResponse.setHeader("X-Presented-By", presenter);
        httpServletResponse.setHeader("X-Powered-By", "Hopes and dreams");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
