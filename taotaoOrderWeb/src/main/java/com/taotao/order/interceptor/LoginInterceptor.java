package com.taotao.order.interceptor;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 * Created by 孙建荣 on 17-6-19.下午5:38
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Value("${TT_TOKEN}")
    private String TT_TOKEN;

    @Value("${SSO_URL}")
    private String SSO_URL;

    @Autowired
    private UserService userService;

    /**
     * Intercept the execution of a handler. Called after HandlerMapping determined
     * an appropriate handler object, but before HandlerAdapter invokes the handler.
     * <p>
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can decide to abort the execution chain,
     * typically sending a HTTP error or writing a custom response.
     * <p>
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  chosen handler to execute, for type and/or instance evaluation
     * @return {@code true} if the execution chain should proceed with the
     * next interceptor or the handler itself. Else, DispatcherServlet assumes
     * that this interceptor has already dealt with the response itself.
     * @throws Exception in case of errors
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 执行handler之前执行这个方法
        // 从cookie中取出token信息
        String token = CookieUtils.getCookieValue(request, TT_TOKEN);
        // 如果娶不到token,跳转到sso的登录页面,把当前的url传递给sso
        if (StringUtils.isBlank(token)) {
            String url = request.getRequestURL().toString();
            // 跳转到登录页面
            response.sendRedirect(SSO_URL + "/page/login?url=" + url);
            // 拦截
            return false;
        }
        // 渠道token,调用sso系统的服务判断用户是否登录
        TaotaoResult result = userService.getUserByToken(token);
        if (result.getStatus() != 200) {
            // 没有取到用户信息
            String url = request.getRequestURL().toString();
            // 跳转到登录页面
            response.sendRedirect(SSO_URL + "/page/login?url=" + url);
            // 拦截
            return false;
        }
        // 如果用户登录就
        // 把用户信息放到request里面去
        TbUser tbUser = (TbUser) result.getData();
        request.setAttribute("user", tbUser);
        return true;
        // 返回true就放行
    }

    /**
     * Intercept the execution of a handler. Called after HandlerAdapter actually
     * invoked the handler, but before the DispatcherServlet renders the view.
     * Can expose additional model objects to the view via the given ModelAndView.
     * <p>
     * <p>DispatcherServlet processes a handler in an execution chain, consisting
     * of any number of interceptors, with the handler itself at the end.
     * With this method, each interceptor can post-process an execution,
     * getting applied in inverse order of the execution chain.
     * <p>
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     *
     * @param request      current HTTP request
     * @param response     current HTTP response
     * @param handler      handler (or {@link HandlerMethod}) that started async
     *                     execution, for type and/or instance examination
     * @param modelAndView the {@code ModelAndView} that the handler returned
     *                     (can also be {@code null})
     * @throws Exception in case of errors
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * Callback after completion of request processing, that is, after rendering
     * the view. Will be called on any outcome of handler execution, thus allows
     * for proper resource cleanup.
     * <p>
     * <p>Note: Will only be called if this interceptor's {@code preHandle}
     * method has successfully completed and returned {@code true}!
     * <p>
     * <p>As with the {@code postHandle} method, the method will be invoked on each
     * interceptor in the chain in reverse order, so the first interceptor will be
     * the last to be invoked.
     * <p>
     * <p><strong>Note:</strong> special considerations apply for asynchronous
     * request processing. For more details see
     * {@link AsyncHandlerInterceptor}.
     *
     * @param request  current HTTP request
     * @param response current HTTP response
     * @param handler  handler (or {@link HandlerMethod}) that started async
     *                 execution, for type and/or instance examination
     * @param ex       exception thrown on handler execution, if any
     * @throws Exception in case of errors
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
