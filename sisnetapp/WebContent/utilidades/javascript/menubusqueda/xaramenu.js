var ma = new Array();
var mx = new Array();
var my = new Array();
var mc = new Array();
var mpos = new Array();
var mal = 0;
var main = 0;
var menuw = 200;
var psrc = 0;
var pname = "";
var al = "";
var gd = 0;
var gx, gy;
var d = document;
var NS7 = (!d.all && d.getElementById);
var NS4 = (!d.getElementById);
var IE5 = (!NS4 && !NS7 && (navigator.userAgent.indexOf('MSIE 5.0') != -1 || navigator.userAgent.indexOf('MSIE 5.2') != -1));
var IE5p5 = (!NS4 && !NS7 && navigator.userAgent.indexOf('MSIE 5.5') != -1);
var NS6 = (NS7 && navigator.userAgent.indexOf('Netscape6') != -1);
var SAF = navigator.userAgent.indexOf('Safari') != -1;
p = navigator.userAgent.indexOf('Opera');

if (p > -1) {
	p = navigator.userAgent.charAt(p + 6);
	if (p > 6)
		NS7 = 1;
	else
		NS4 = 1;
}

var ifr = (!NS7 && !NS4 && !IE5 && !IE5p5);
var quirk = (d.compatMode && d.compatMode == "BackCompat") || IE5 || IE5p5;

function startMainMenu(file, h, w, dir, space, align) {
	if (w > 0)
		menuw = w;
	main = dir;
	if (main == 1 || main == 2)
		d.write("<table border=\"0\" cellspacing=\"" + space + "\" cellpadding=\"0\">");
	if (file != "") {
		al = "";
		if (align == 1)
			al = " align=\"right\"";
		if (align == 2)
			al = " align=\"center\"";
		if (main == 1 || main == 2)
			d.write("<tr style='line-height:1px'><td" + al + ">");
		d.write("<img src=\"" + loc + file + "\" border=\"0\"");
		if (h > 0)
			d.write(" height=\"" + h + "\"");
		if (w > 0)
			d.write(" width=\"" + w + "\"");
		d.write(" />");
		if (main == 1 || main == 2)
			d.write("</td>");
		if (main == 1)
			d.write("</tr>");
		if (main == 3)
			d.write("<br />");
	}
}

function endMainMenu(file, h, w) {
	if (file != "") {
		if (main == 1)
			d.write("<tr>");
		if (main == 1 || main == 2)
			d.write("<td" + al + ">");
		d.write("<img src=\"" + loc + file + "\" border=\"0\"");
		if (h > 0)
			d.write(" height=\"" + h + "\"");
		if (w > 0)
			d.write(" width=\"" + w + "\"");
		d.write(" />");
		if (main == 1 || main == 2)
			d.write("</td></tr>");
	}
	if (main == 1 || main == 2)
		d.write("</table>");
	main = 0;
}

function mainMenuItem(name, ext, h, w, url, tar, alt, dir, state, s, cim) {
	if (NS4 && main == 0) return;
	var isgraphic = ext.charAt(0) == ".";
	if (main == 1)
		d.write("<tr>");
	if (main == 1 || main == 2)
		d.write("<td" + al + ">");
	d.write("<a ");
	if (url != "" || !isgraphic) {
		if (typeof (clx) != "undefined") {
			url = "?" + clx; tar = "";
			alt = "Haga click para editar";
		}
		d.write("href=\"" + url + "\" ");
	}
	if (tar != "")
		d.write("target=\"" + tar + "\" ");
	d.write("onmouseout=\"");
	if (dir > 0)
		d.write("tidyMenu(event);");
	if (isgraphic)
		d.write("\" onclick=\"");
	else
		d.write("\" onmouseover=\"");
	if (dir > 0)
		d.write("openMenu(event, '" + name + "'," + dir + "," + bc + "," + fc + ");");
	if (state > 1 && isgraphic) {
		d[name + "_over"] = new Image();
		d[name + "_over"].src = loc + name + "_over" + ext;
		d.write("setGraphic(event, '" + loc + name + "_over" + ext + "');");
	}
	d.write("return false;\"");
	if (!isgraphic)
		d.write(" class=\"" + s + "\" style=\"width:" + (w > 0 ? w : menuw) + "px\"");
	d.write(">");
	if (isgraphic) {
		d.write("<img src=\"" + loc + name + ext + "\" border=\"0\"");
		if (h > 0)
			d.write(" height=\"" + h + "\"");
		if (w > 0)
			d.write(" width=\"" + w + "\"");
		if (alt != "")
			d.write(" alt=\"" + alt + "\" title=\"" + alt + "\"");
		if (cim != "")
			d.write(" data-customimg=\"true\"");

		d.write(" />");
	}
	else {
		d.write("&nbsp;" + ext + "&nbsp;");
	}
	d.write("</a>");
	if (main == 1 || main == 2)
		d.write("</td>");
	if (main == 1)
		d.write("</tr>");
	if (main == 3)
		d.write("<br />");
}

function startSubmenu(name, style, sw) {
	var depth = name.split("_").length + 1000;
	if (NS4) return;
	if (sw > 0) menuw = sw;
	d.write("<div id=\"" + name + "\" class=\"" + style + "\"  style=\"z-index:" + depth + ";width:" + (menuw + (NS7 ? bd * 2 : 0)) + "px\">");
}

function endSubmenu(name) {
	if (NS4) return;
	d.write("</div>");
	if (!NS7) d.getElementById(name).onmouseout = tidyMenu;
}

function submenuItem(text, url, tar, s, click) {
	if (NS4) return;
	if (text.charAt(0) == '<')
		d.write(text);
	else
		if (text == "---")
			d.write("<div class=\"" + s + "\" style=\"width:" + menuw + "px\"><center><img src=\"" + loc + "---.gif\" height=\"8\" width=\"" + (menuw - 6 - (2 * bd)) + "\" border=0/></center></div>");
		else {
			d.write("<a ");
			if (url != "")
				d.write("href=\"" + url + "\" ");
			if (tar != "")
				d.write("target=\"" + tar + "\" ");
			if (click != "")
				d.write("onClick=\"" + click + "\" ");
			d.write("class=\"" + s + "\" style=\"width:" + menuw + "px\">&nbsp;" + text + "&nbsp;</a>");
		}
}

function setGraphic(event, name) {
	if (NS4) return;
	psrc = (NS7) ? event.target : event.srcElement; pname = psrc.src;
	if (NS7)
		event.target.src = name;
	else
		event.srcElement.src = name;
}

function openMenu(event, id, pos, bc, fc) {
	if (NS4) return;
	var el, x, y, dx, dy;
	var bwidth;
	if (gd == 0 || mal == 0) {
		var p = d.getElementById(id);
		gx = 0;
		gy = 0;
		while (p && p.offsetParent) {
			p = p.offsetParent;
			gx += p.offsetLeft;
			gy += p.offsetTop;
		}
		if (p)
			gd = 1;
	}
	if (mal > 0) {
		el = d.getElementById(ma[mal - 1]);
		if (mx[mal - 1] != el.offsetLeft || my[mal - 1] != el.offsetTop) {
			el.style.left = mx[mal - 1] + "px";
			el.style.top = my[mal - 1] + "px";
		}
		tidyMenu(event);
	}
	if (mal > 1)
		pos = mpos[mal - 1];
	if (NS7) {
		bwidth = window.innerWidth;
		var p = event.target;
		if (p.nodeName != "A" && p.nodeName != "IMG" && p.parentNode.nodeName == "A")
			p = p.parentNode;
		
		// Usar getBoundingClientRect para obtener dimensiones escaladas
        var rect = p.getBoundingClientRect();
        dx = rect.width;
        dy = rect.height;

        /*
		dx = p.offsetWidth;
		dy = p.offsetHeight;
		*/
		
		if (mal == 0) {
			
			x = rect.left + window.pageXOffset;
			y = rect.top + window.pageYOffset;
			//x = p.x;
			//y = p.y;
			
			if (typeof (p.x) == "undefined" || (!NS6 && !SAF)) {
				x = p.offsetLeft;
				y = p.offsetTop;
				while (!NS6 && p.parentNode.nodeName != "BODY") {
					p = p.parentNode;
					if (p.nodeName == "TD" || p.nodeName == "TABLE") {
						x += p.offsetLeft; y += p.offsetTop;
					}
				}
			}
		}
		else {
			el = d.getElementById(ma[mal - 1]);
			x = el.offsetLeft;
			y = el.offsetTop + p.offsetTop;
		}
		if (pos != 3)
			x -= bd;
		if (pos == 3 && mal > 0)
			x += bd;
	}
	else {
		bwidth = document.body.clientWidth;
		x = event.clientX - event.offsetX - d.body.clientLeft - gx;
		y = event.clientY - event.offsetY - d.body.clientTop - gy;
		dx = event.srcElement.offsetWidth;
		dy = event.srcElement.offsetHeight;
		if (!quirk) {
			x += d.documentElement.scrollLeft - 2;
			y += d.documentElement.scrollTop - 2;
		}
		else {
			x += d.body.scrollLeft;
			y += d.body.scrollTop;
		}
		if (mal > 0) {
			y -= bd;
			if (pos != 3) x -= 2 * bd;
		}
	}
	el = d.getElementById(id);
	if (el && el.style.visibility != "visible") {
		if (pos == 1) {
			x += dx;
			el.style.left = x - el.offsetWidth + "px";
			el.style.top = y + "px";
			nspeed = el.offsetWidth / frames;
			if (x + gx + el.offsetWidth > bwidth) {
				x -= dx; pos = 3;
			}
		}
		else
			if (pos == 2) {
				y += dy;
				el.style.left = x + "px";
				el.style.top = y - el.offsetHeight + "px";
				nspeed = el.offsetHeight / frames;
			}
		if (pos == 3) {
			x -= el.offsetWidth;
			el.style.left = x + el.offsetWidth + "px";
			el.style.top = y + "px";
			nspeed = el.offsetWidth / frames;
			if (x + gx < 0) {
				x += el.offsetWidth;
				pos = 1;
				x += dx;
				el.style.left = x - el.offsetWidth + "px";
				el.style.top = y + "px";
				nspeed = el.offsetWidth / frames;
			}
		}
		mx[mal] = x;
		my[mal] = y;
		if (NS7 || IE5 || frames == 0) {
			el.style.left = x + "px"; 
			el.style.top = y + "px";
		}
		if (!IE5)
			clipMenu(mal, el);
		el.style.visibility = "visible";
		ma[mal] = id;
		mpos[mal] = pos;
		if (NS7) {
			var p = event.target;
			if (p.nodeName != "A" && p.parentNode.nodeName == "A")
				p = p.parentNode; 
				mc[mal] = p.style;
			if (!NS6 && mal > 0) {
				mc[mal].backgroundColor = "#" + bc.toString(16);
				mc[mal].color = "#" + fc.toString(16);
			}
			el.onmouseout = tidyMenu;
		}
		else {
			mc[mal] = event.srcElement.style;
			if (mal > 0) {
				mc[mal].backgroundColor = bc;
				mc[mal].color = fc;
			}
		}
		mal++;
	}

	if (ifr && el && frames == 0 && (typeof (tr) == "undefined" || tr == "")) {
		var p = d.getElementById(id + "i");
		if (p) {
			p.style.top = y + "px";
			p.style.left = x + "px";
			p.style.width = el.style.width;
			p.style.height = el.offsetHeight + "px";
			p.style.display = "block";
		}
		else {
			ifr = "<iframe id=\"" + id + "i\" style=\"position:absolute;left:" + x + "px;width:" + el.style.width + ";height:" + el.offsetHeight + "px;top:" + y + "px;z-index:998;display:block;\" scrolling=\"no\" frameborder=\"0\"></iframe>";
			el.insertAdjacentHTML('beforeBegin', ifr);
		}
	}
}

function overMenu(x, y) {
	x -= gx;
	y -= gy;
	for (i = mal - 1; i >= 0; i--) {
		var el = d.getElementById(ma[i]);
		if (el.offsetLeft + el.offsetWidth > x && el.offsetLeft <= x && el.offsetTop + el.offsetHeight > y && el.offsetTop <= y) {
			return ma[i];
		}
	}
	return "";
}

function tidyMenu(e) {
	if (NS4) return;
	if (NS7) {
		t = overMenu(e.pageX, e.pageY);
		if (t != "" && (e.target.firstChild == e.relatedTarget || e.target == e.relatedTarget.firstChild))
			return;
	}
	else {
		var x = event.clientX - d.body.clientLeft;
		var y = event.clientY - d.body.clientTop;
		if (!quirk) {
			x += d.documentElement.scrollLeft - 2;
			y += d.documentElement.scrollTop - 2;
		}
		else {
			x += d.body.scrollLeft;
			y += d.body.scrollTop;
		}
		t = overMenu(x, y);
	}
	om = 0;
	for (i = 0; i < mal; i++) {
		var mail = ma[i].length;
		if (mail > t.length || t.substring(0, mail) != ma[i]) {
			var el = d.getElementById(ma[i]);
			el.style.visibility = "hidden";
			mc[i].backgroundColor = "";
			mc[i].color = "";
			if (ifr) {
				var p = d.getElementById(ma[i] + "i");
				if (p)
					p.style.display = "none";
			}
		}
		else {
			ma[om] = ma[i];
			mx[om] = mx[i];
			my[om] = my[i];
			om++;
		}
	}
	mal = om;
	if (mal == 0 && psrc){
		psrc.src = pname.replace("_hover", "").replace("_focus", "");	
	}
		
}

function animate() {
	for (i = 0; i < mal; i++) {
		var el = d.getElementById(ma[i]);
		if (el.style.visibility == "visible") {
			if (el.offsetLeft < mx[i])
				el.style.left = Math.min(el.offsetLeft + nspeed, mx[i]) + "px";
			if (el.offsetLeft > mx[i])
				el.style.left = Math.max(el.offsetLeft - nspeed, mx[i]) + "px";
			if (el.offsetTop < my[i])
				el.style.top = Math.min(el.offsetTop + nspeed, my[i]) + "px";
			clipMenu(i, el);
		}
	}
	if (mal != 0 || frames != 0)
		setTimeout("animate()", 50);
}

function clipMenu(i, el) {
	if (el.offsetLeft > mx[i])
		el.style.clip = "rect(" + (my[i] - el.offsetTop) + "px " + (el.offsetWidth + (mx[i] - el.offsetLeft)) + "px " + el.offsetHeight + "px " + 0 + "px)";
	else el.style.clip = "rect(" + (my[i] - el.offsetTop) + "px " + el.offsetWidth + "px " + el.offsetHeight + "px " + (mx[i] - el.offsetLeft) + "px)";
}