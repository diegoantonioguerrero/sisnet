//©Xara Ltd
if(typeof(loc)=="undefined"||loc==""){var loc="";if(document.body&&document.body.innerHTML){var tt=document.body.innerHTML;var ml=tt.match(/["']([^'"]*)menubusqueda.js["']/i);if(ml && ml.length > 1) loc=ml[1];}}

var bd=2
document.write("<style type=\"text/css\">");
document.write("\n<!--\n");
document.write(".menubusqueda_menu {z-index:999;border-color:#949494;border-style:solid;border-width:"+bd+"px 0px "+bd+"px 0px;background-color:#bfc0c0;position:absolute;left:0px;top:0px;visibility:hidden;}");
document.write(".menubusqueda_plain, a.menubusqueda_plain:link, a.menubusqueda_plain:visited{text-align:left;background-color:#bfc0c0;color:#000000;text-decoration:none;border-color:#949494;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:9pt;font-family:Tahoma, Verdana, Arial, sans-serif;}");
document.write("a.menubusqueda_plain:hover, a.menubusqueda_plain:active{background-color:#00375d;color:#ffffff;text-decoration:none;border-color:#949494;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:9pt;font-family:Tahoma, Verdana, Arial, sans-serif;}");
document.write("a.menubusqueda_l:link, a.menubusqueda_l:visited{text-align:left;background:#bfc0c0 url("+loc+"menubusqueda_l.gif) no-repeat right;color:#000000;text-decoration:none;border-color:#949494;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:9pt;font-family:Tahoma, Verdana, Arial, sans-serif;}");
document.write("a.menubusqueda_l:hover, a.menubusqueda_l:active{background:#00375d url("+loc+"menubusqueda_l2.gif) no-repeat right;color: #ffffff;text-decoration:none;border-color:#949494;border-style:solid;border-width:0px "+bd+"px 0px "+bd+"px;padding:2px 0px 2px 0px;cursor:hand;display:block;font-size:9pt;font-family:Tahoma, Verdana, Arial, sans-serif;}");
document.write("\n-->\n");
document.write("</style>");

var fc=0xffffff;
var bc=0x00375d;
if(typeof(frames)=="undefined"){var frames=3;if(frames>0)animate();}

