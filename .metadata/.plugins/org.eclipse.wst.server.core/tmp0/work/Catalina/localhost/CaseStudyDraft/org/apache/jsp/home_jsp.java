/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.90
 * Generated at: 2018-09-12 05:11:09 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class home_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(1);
    _jspx_dependants.put("/header.jsp", Long.valueOf(1536729058958L));
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005fif_0026_005ftest;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<!-- <!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\"> -->\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n");
      out.write("\t<title>Home Page</title>\r\n");
      out.write("\t<script src=\"script/home.js\"></script> \r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"css/main.css\" />\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t");
      out.write("<!-- HEADER PART, ONLY MEANT TO BE INCLUDED INTO SEVERAL OTHER PAGES -->\r\n");
      out.write("<div class=\"header\">\r\n");
      out.write("\t<h2>Inventory Management System</h2>\r\n");
      out.write("\t<div class=\"nav\">\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t  <li><a id=\"navHome\" href=\"home.jsp\">Home</a></li>\r\n");
      out.write("\t\t  <li><a id=\"navInventory\" href=\"ProductManagement\">Inventory</a></li>\r\n");
      out.write("\t\t  ");
      if (_jspx_meth_c_005fif_005f0(_jspx_page_context))
        return;
      out.write("\r\n");
      out.write("\t\t  <li><a id=\"navProfile\" href=\"\">Profile</a></li>\r\n");
      out.write("\t\t  <li><a id=\"\" href=\"Login?action=logout\">Log Out</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</div>\r\n");
      out.write("</div>");
      out.write("\r\n");
      out.write("\t<div class=\"content\">\r\n");
      out.write("\t\t<h6>Hello ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.username}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write(", you have successfully logged in!</h6>\r\n");
      out.write("<!-- \t\t<button type=\"button\" name=\"btnProd\" onclick=\"location.href='ProductManagement'\">Product List</button> <br> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t\t\t<button type=\"button\" name=\"btnManageUsers\" onclick=\"location.href='UserManagement'\">Manage User Accounts</button> <br> -->\r\n");
      out.write("\r\n");
      out.write("<!-- \t\t<button type=\"button\" name=\"btnLogOut\" onclick=\"location.href='Login?action=logout'\">Log Out</button> <br> -->\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t\ta<br />\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005fif_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:if
    org.apache.taglibs.standard.tag.rt.core.IfTag _jspx_th_c_005fif_005f0 = (org.apache.taglibs.standard.tag.rt.core.IfTag) _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.get(org.apache.taglibs.standard.tag.rt.core.IfTag.class);
    boolean _jspx_th_c_005fif_005f0_reused = false;
    try {
      _jspx_th_c_005fif_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005fif_005f0.setParent(null);
      // /header.jsp(8,4) name = test type = boolean reqTime = true required = true fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005fif_005f0.setTest(((java.lang.Boolean) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${user.userType == 'admin'}", java.lang.Boolean.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false)).booleanValue());
      int _jspx_eval_c_005fif_005f0 = _jspx_th_c_005fif_005f0.doStartTag();
      if (_jspx_eval_c_005fif_005f0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\r\n");
          out.write("\t\t  \t<li><a id=\"navUser\" href=\"UserManagement\" onload=\"setActiveNavTab()\">User Management</a></li>\r\n");
          out.write("\t\t  ");
          int evalDoAfterBody = _jspx_th_c_005fif_005f0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_005fif_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005fif_0026_005ftest.reuse(_jspx_th_c_005fif_005f0);
      _jspx_th_c_005fif_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005fif_005f0, _jsp_getInstanceManager(), _jspx_th_c_005fif_005f0_reused);
    }
    return false;
  }
}
