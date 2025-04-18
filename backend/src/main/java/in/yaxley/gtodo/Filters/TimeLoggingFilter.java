package in.yaxley.gtodo.Filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
@Slf4j
class TimeLoggingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long time = System.currentTimeMillis();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            time = System.currentTimeMillis() - time;
            String method = ((HttpServletRequest) servletRequest).getMethod();
            log.atInfo().log("{} {} Processed in: {}ms", method, ((HttpServletRequest) servletRequest).getRequestURI(), time);
        }
    }
}
