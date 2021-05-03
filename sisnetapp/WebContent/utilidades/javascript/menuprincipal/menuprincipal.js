//©Xara Ltd
if(typeof(loc)=="undefined"||loc==""){
    var loc="";
    if(document.body&&document.body.innerHTML){
        var tt=document.body.innerHTML;
        var ml=tt.match(/["']([^'"]*)menuprincipal.js["']/i);
        if(ml && ml.length > 1) loc=ml[1];
    }
}

var bd=1;
document.write("<style type=\"text/css\">");
document.write("\n<!--\n");
document.write(".menuprincipal_menu {\n\
                    z-index:999;\n\
                    border-color:#868684;\n\
                    border-style:solid;\n\
                    border-width:2px 2px 2px 2px;\n\
                    background-color:#cecece;\n\
                    position:absolute;\n\
                    left:0px;\n\
                    top:0px;\n\
                    visibility:hidden;\n\
               }");
document.write(".menuprincipal_plain, a.menuprincipal_plain:link, a.menuprincipal_plain:visited{\n\
                    text-align:left;\n\
                    background-color:#cecece;\n\
                    color:#000000;\n\
                    text-decoration:none;\n\
                    border-color:#868684;\n\
                    border-style:solid;\n\
                    border-width:0px "+bd+"px 0px "+bd+"px;\n\
                    padding:2px 0px 2px 0px;\n\
                    cursor:hand;\n\
                    display:block;\n\
                    font-size:8pt;\n\
                    font-family:Arial, Helvetica, sans-serif;\n\
                }");
document.write("a.menuprincipal_plain:hover, a.menuprincipal_plain:active{\n\
                    background-color:#050668;\n\
                    color:#ffffff;\n\
                    text-decoration:none;\n\
                    border-color:#868684;\n\
                    border-style:solid;\n\
                    border-width:0px "+bd+"px 0px "+bd+"px;\n\
                    padding:2px 0px 2px 0px;\n\
                    cursor:hand;\n\
                    display:block;\n\
                    font-size:8pt;\n\
                    font-family:Arial, Helvetica, sans-serif;\n\
                }");
document.write("a.menuprincipal_l:link, a.menuprincipal_l:visited{\n\
                    text-align:left;\n\
                    background:#cecece url("+loc+"menuprincipal_l.gif) no-repeat right;\n\
                    color:#000000;\n\
                    text-decoration:none;\n\
                    border-color:#868684;\n\
                    border-style:solid;\n\
                    border-width:0px "+bd+"px 0px "+bd+"px;\n\
                    padding:2px 0px 2px 0px;\n\
                    cursor:hand;\n\
                    display:block;\n\
                    font-size:8pt;\n\
                    font-family:Arial, Helvetica, sans-serif;\n\
                }");
document.write("a.menuprincipal_l:hover, a.menuprincipal_l:active{\n\
                    background:#050668 url("+loc+"menuprincipal_l2.gif) no-repeat right;\n\
                    color: #ffffff;\n\
                    text-decoration:none;\n\
                    border-color:#868684;\n\
                    border-style:solid;\n\
                    border-width:0px "+bd+"px 0px "+bd+"px;\n\
                    padding:2px 0px 2px 0px;\n\
                    cursor:hand;\n\
                    display:block;\n\
                    font-size:8pt;\n\
                    font-family:Arial, Helvetica, sans-serif;\n\
                }");
document.write("\n-->\n");
document.write("</style>");

var fc=0xffffff;
var bc=0x050668;
if(typeof(frames)=="undefined"){var frames=0;}

