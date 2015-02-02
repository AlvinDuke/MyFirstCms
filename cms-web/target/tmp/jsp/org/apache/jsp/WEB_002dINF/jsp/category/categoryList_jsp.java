package org.apache.jsp.WEB_002dINF.jsp.category;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class categoryList_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.apache.jasper.runtime.TagHandlerPool _jspx_tagPool_c_forEach_var_items;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _jspx_tagPool_c_forEach_var_items = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _jspx_tagPool_c_forEach_var_items.release();
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n");
      out.write("<html lang=\"en\">\n");
      out.write("<head>\n");
      out.write("<meta charset=\"utf-8\">\n");
      out.write("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
      out.write("<meta name=\"description\" content=\"\">\n");
      out.write("<meta name=\"author\" content=\"\">\n");
      out.write("\n");
      out.write("<title>栏目管理</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("\t<!-- ztree css -->\n");
      out.write("\t<link rel=\"stylesheet\" href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/css/zTreeStyle/zTreeStyle.css\" type=\"text/css\">\n");
      out.write("\t\n");
      out.write("\t<div class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">\n");
      out.write("\t\t<div class=\"page-header\">\n");
      out.write("\t\t\t<h1>栏目中心</h1>\n");
      out.write("\t\t</div>\n");
      out.write("\t\t\n");
      out.write("\t\t<div class=\"row placeholders\">\n");
      out.write("\t\t\n");
      out.write("\t\t\t<div class=\"col-xs-6 col-sm-3 placeholder\">\n");
      out.write("\t\t\t\t<ul id=\"tree\" class=\"ztree\"></ul>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t\t当前栏目[");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.name}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("]<a href=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/category/add/");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\" class=\"btn btn-sm btn-primary\">添加栏目</a>\n");
      out.write("\t\t\t<div class=\"table-responsive\">\n");
      out.write(" \t\t\t\t<table class=\"table table-striped\">\n");
      out.write("\t\t\t\t\t<thead>\n");
      out.write("\t\t\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t\t\t<th>栏目名称</th>\n");
      out.write("\t\t\t\t\t\t\t<th>栏目类别</th>\n");
      out.write("\t\t\t\t\t\t\t<th>定制栏目</th>\n");
      out.write("\t\t\t\t\t\t\t<th>推荐栏目</th>\n");
      out.write("\t\t\t\t\t\t\t<th>状态</th>\n");
      out.write("\t\t\t\t\t\t\t<th>操作</th>\n");
      out.write("\t\t\t\t\t\t</tr>\n");
      out.write("\t\t\t\t\t</thead>\n");
      out.write("\t\t\t\t\t<tbody id=\"sortable\">\n");
      out.write("\t\t\t\t\t\t");
      if (_jspx_meth_c_forEach_0(_jspx_page_context))
        return;
      out.write("\n");
      out.write("\t\t\t\t\t</tbody>\n");
      out.write("\t\t\t\t</table>\n");
      out.write("\t\t\t\t<a href=\"javascript:;\" class=\"btn btn-sm btn-primary\" id=\"setUpSort\">拖动栏目重新排序</a>\n");
      out.write("\t\t\t\t<a href=\"javascript:;\" class=\"btn btn-sm btn-primary\" id=\"saveSort\">保存当前栏目排序</a>\n");
      out.write("\t\t\t</div>\n");
      out.write("\t\t</div>\n");
      out.write("\t</div>\n");
      out.write("</ul>\n");
      out.write("\t<!-- zTree -->\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/js/jquery.ztree.core-3.5.js\"></script>\n");
      out.write("\t<!-- jQuery sortable -->\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/js/jquery.ui.core.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/js/jquery.ui.widget.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/js/jquery.ui.mouse.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/js/jquery.ui.sortable.js\"></script>\n");
      out.write("\t<!-- 自定义 -->\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/jspJs/common/commonList.js\"></script>\n");
      out.write("\t<script src=\"");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("/resources/jspJs/category/categoryList.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t\tvar zNodes = ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${treeData}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(";\n");
      out.write("\t\t$(function() {\n");
      out.write("\t\t\t//保存当前表格的初始化顺序\n");
      out.write("\t\t\tvar defaultOrders = [];\n");
      out.write("\t\t\t$(\"#sortable tr\").each(function(){\n");
      out.write("\t\t\t\tdefaultOrders.push(this.id);\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t//是否处于排序状态\n");
      out.write("\t\t\tvar isSortStatus = false;\n");
      out.write("\t\t\t//保存排序默认不可用\n");
      out.write("\t\t\t$(\"#saveSort\").attr(\"disabled\",\"disabled\");\n");
      out.write("\t\t\t//排序表格初始化\n");
      out.write("\t\t\t$(\"#sortable\").sortable({\n");
      out.write("\t\t\t\taxis: \"y\",\n");
      out.write("\t\t\t\tcursor: \"move\",\n");
      out.write("\t\t\t\thelper: function(e,ele){\n");
      out.write("\t\t\t\t\t//拖动时保持宽度不变\n");
      out.write("\t\t\t\t\tvar $ori = ele.children();\n");
      out.write("\t\t\t\t\tvar $helper = ele.clone();\n");
      out.write("\t\t\t\t\t$helper.children().each(function(index){\n");
      out.write("\t\t\t\t\t\t$(this).width($ori.eq(index).width());\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t\treturn $helper;\n");
      out.write("\t\t\t\t},\n");
      out.write("\t\t\t\tupdate: function() {\n");
      out.write("\t\t\t\t\tinitSortOrders();\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\t$(\"#sortable\").disableSelection().sortable(\"disable\");\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t//开启排序\n");
      out.write("\t\t\t$(\"#setUpSort\").click(function(){\n");
      out.write("\t\t\t\t$(\"#sortable\").sortable(\"enable\");\n");
      out.write("\t\t\t\tinitSortOrders();\n");
      out.write("\t\t\t\t//排序按钮不可用\n");
      out.write("\t\t\t\t$(this).attr(\"disabled\",\"disabled\");\n");
      out.write("\t\t\t\t//保存排序可用\n");
      out.write("\t\t\t\t$(\"#saveSort\").removeAttr(\"disabled\");\n");
      out.write("\t\t\t\tisSortStatus = true;\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t//保存排序\n");
      out.write("\t\t\t$(\"#saveSort\").click(function(){\n");
      out.write("\t\t\t\tvar curOrders = $(\"#sortable\").sortable(\"toArray\");\n");
      out.write("\t\t\t\t//检查排序是否产生变化，有变化则保存\n");
      out.write("\t\t\t\tfor(var i = 0;i < defaultOrders.length;i++){\n");
      out.write("\t\t\t\t\t//排序有变化则保存\n");
      out.write("\t\t\t\t\tif(defaultOrders[i] != curOrders[i]){\n");
      out.write("\t\t\t\t\t\t$.ajax({\n");
      out.write("\t\t\t\t\t    \turl: root + \"/category/updateOrders?cids=\"+curOrders,\n");
      out.write("\t\t\t\t\t\t}).done(function(data){\n");
      out.write("\t\t\t\t\t\t\tif(data == \"success\"){\n");
      out.write("\t\t\t\t\t\t\t\talert(\"保存成功\");\n");
      out.write("\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t}).fail(function(data){\n");
      out.write("\t\t\t\t\t\t\tif(data == \"fail\"){\n");
      out.write("\t\t\t\t\t\t\t\talert(\"保存失败，请重试\");\n");
      out.write("\t\t\t\t\t\t\t}\n");
      out.write("\t\t\t\t\t\t});\n");
      out.write("\t\t\t\t\t\tbreak;\n");
      out.write("\t\t\t\t\t}\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t//清除排序序号\n");
      out.write("\t\t\t\t$(\"thead tr th:last\").remove();\n");
      out.write("\t\t\t\t$(\"#sortable tr\").each(function(index){\n");
      out.write("\t\t\t\t\t$(this).find(\"td:last\").remove();\t\t\t\t\n");
      out.write("\t\t\t\t});\n");
      out.write("\t\t\t\t\n");
      out.write("\t\t\t\t$(\"#sortable\").sortable(\"disable\");\n");
      out.write("\t\t\t\t$(this).attr(\"disabled\",\"disabled\");\n");
      out.write("\t\t\t\t//排序可用\n");
      out.write("\t\t\t\t$(\"#setUpSort\").removeAttr(\"disabled\");\n");
      out.write("\t\t\t\tisSortStatus = false;\n");
      out.write("\t\t\t});\n");
      out.write("\t\t\t\n");
      out.write("\t\t\t//初始化排序，添加直观的排序序号\n");
      out.write("\t\t\tfunction initSortOrders(){\n");
      out.write("\t\t\t\tif(isSortStatus){\n");
      out.write("\t\t\t\t\t$(\"#sortable tr\").each(function(index){\n");
      out.write("\t\t\t\t\t\t$(this).find(\"td:last\").html(index+1);\t\t\t\t\t\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t\telse{\n");
      out.write("\t\t\t\t\t$(\"thead tr:last\").append(\"<th>排序</th>\");\n");
      out.write("\t\t\t\t\t$(\"#sortable tr\").each(function(index){\n");
      out.write("\t\t\t\t\t\t$(this).append(\"<td>\" + (index+1) + \"</td>\");\t\t\t\t\t\n");
      out.write("\t\t\t\t\t});\n");
      out.write("\t\t\t\t}\n");
      out.write("\t\t\t}\n");
      out.write("\t\t});\n");
      out.write("\t</script>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_forEach_0(PageContext _jspx_page_context)
          throws Throwable {
    PageContext pageContext = _jspx_page_context;
    JspWriter out = _jspx_page_context.getOut();
    //  c:forEach
    org.apache.taglibs.standard.tag.rt.core.ForEachTag _jspx_th_c_forEach_0 = (org.apache.taglibs.standard.tag.rt.core.ForEachTag) _jspx_tagPool_c_forEach_var_items.get(org.apache.taglibs.standard.tag.rt.core.ForEachTag.class);
    _jspx_th_c_forEach_0.setPageContext(_jspx_page_context);
    _jspx_th_c_forEach_0.setParent(null);
    _jspx_th_c_forEach_0.setItems((java.lang.Object) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${cs}", java.lang.Object.class, (PageContext)_jspx_page_context, null));
    _jspx_th_c_forEach_0.setVar("c");
    int[] _jspx_push_body_count_c_forEach_0 = new int[] { 0 };
    try {
      int _jspx_eval_c_forEach_0 = _jspx_th_c_forEach_0.doStartTag();
      if (_jspx_eval_c_forEach_0 != javax.servlet.jsp.tagext.Tag.SKIP_BODY) {
        do {
          out.write("\n");
          out.write("\t\t\t\t\t\t\t<tr id=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\">\n");
          out.write("\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.name }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.categoryType.name }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.isCustom == 0?'否':'是' }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.isRecommended == 0?'否':'是'  }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td>");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.status == 0?'停用':'启用' }", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("</td>\n");
          out.write("\t\t\t\t\t\t\t\t<td><a href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("/category/delete/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"\n");
          out.write("\t\t\t\t\t\t\t\t\t\tclass=\"btn btn-xs btn-danger deleteObj\">删除</a>\n");
          out.write("\t\t\t\t\t\t\t\t\t<a href=\"");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${ctx}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("/category/update/");
          out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${c.id}", java.lang.String.class, (PageContext)_jspx_page_context, null));
          out.write("\"\n");
          out.write("\t\t\t\t\t\t\t\t\t\tclass=\"btn btn-xs btn-primary\">更新</a></td>\n");
          out.write("\t\t\t\t\t\t\t</tr>\n");
          out.write("\t\t\t\t\t\t");
          int evalDoAfterBody = _jspx_th_c_forEach_0.doAfterBody();
          if (evalDoAfterBody != javax.servlet.jsp.tagext.BodyTag.EVAL_BODY_AGAIN)
            break;
        } while (true);
      }
      if (_jspx_th_c_forEach_0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
    } catch (Throwable _jspx_exception) {
      while (_jspx_push_body_count_c_forEach_0[0]-- > 0)
        out = _jspx_page_context.popBody();
      _jspx_th_c_forEach_0.doCatch(_jspx_exception);
    } finally {
      _jspx_th_c_forEach_0.doFinally();
      _jspx_tagPool_c_forEach_var_items.reuse(_jspx_th_c_forEach_0);
    }
    return false;
  }
}
