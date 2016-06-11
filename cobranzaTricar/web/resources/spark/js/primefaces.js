(function (a) {
    if (a.PrimeFaces) {
        a.PrimeFaces.debug("PrimeFaces already loaded, ignoring duplicate execution.");
        return
    }
    var b = {
        escapeClientId: function (c) {
            return"#" + c.replace(/:/g, "\\:")
        },
        cleanWatermarks: function () {
            $.watermark.hideAll()
        },
        showWatermarks: function () {
            $.watermark.showAll()
        },
        getWidgetById: function (e) {
            for (var d in b.widgets) {
                var c = b.widgets[d];
                if (c && c.id === e) {
                    return c
                }
            }
            return null
        },
        addSubmitParam: function (d, f) {
            var e = $(this.escapeClientId(d));
            for (var c in f) {
                e.append('<input type="hidden" name="' + c + '" value="' + f[c] + '" class="ui-submit-param"/>')
            }
            return this
        }, submit: function (e, d) {
            var c = $(this.escapeClientId(e));
            if (d) {
                c.attr("target", d)
            }
            c.submit().children("input.ui-submit-param").remove()
        }, attachBehaviors: function (d, c) {
            $.each(c, function (f, e) {
                d.bind(f, function (g) {
                    e.call(d, g)
                })
            })
        }, getCookie: function (c) {
            return $.cookie(c)
        }, setCookie: function (d, e, c) {
            $.cookie(d, e, c)
        }, deleteCookie: function (d, c) {
            $.removeCookie(d, c)
        }, cookiesEnabled: function () {
            var c = (navigator.cookieEnabled) ? true : false;
            if (typeof navigator.cookieEnabled === "undefined" && !c) {
                document.cookie = "testcookie";
                c = (document.cookie.indexOf("testcookie") !== -1) ? true : false
            }
            return(c)
        }, skinInput: function (c) {
            c.hover(function () {
                $(this).addClass("ui-state-hover")
            }, function () {
                $(this).removeClass("ui-state-hover")
            }).focus(function () {
                $(this).addClass("ui-state-focus")
            }).blur(function () {
                $(this).removeClass("ui-state-focus")
            });
            c.attr("role", "textbox").attr("aria-disabled", c.is(":disabled")).attr("aria-readonly", c.prop("readonly"));
            if (c.is("textarea")) {
                c.attr("aria-multiline", true)
            }
            return this
        }, skinButton: function (c) {
            c.mouseover(function () {
                var e = $(this);
                if (!c.prop("disabled")) {
                    e.addClass("ui-state-hover")
                }
            }).mouseout(function () {
                $(this).removeClass("ui-state-active ui-state-hover")
            }).mousedown(function () {
                var e = $(this);
                if (!c.prop("disabled")) {
                    e.addClass("ui-state-active").removeClass("ui-state-hover")
                }
            }).mouseup(function () {
                $(this).removeClass("ui-state-active").addClass("ui-state-hover")
            }).focus(function () {
                $(this).addClass("ui-state-focus")
            }).blur(function () {
                $(this).removeClass("ui-state-focus ui-state-active")
            }).keydown(function (f) {
                if (f.keyCode === $.ui.keyCode.SPACE || f.keyCode === $.ui.keyCode.ENTER || f.keyCode === $.ui.keyCode.NUMPAD_ENTER) {
                    $(this).addClass("ui-state-active")
                }
            }).keyup(function () {
                $(this).removeClass("ui-state-active")
            });
            var d = c.attr("role");
            if (!d) {
                c.attr("role", "button")
            }
            c.attr("aria-disabled", c.prop("disabled"));
            return this
        }, skinSelect: function (c) {
            c.mouseover(function () {
                var d = $(this);
                if (!d.hasClass("ui-state-focus")) {
                    d.addClass("ui-state-hover")
                }
            }).mouseout(function () {
                $(this).removeClass("ui-state-hover")
            }).focus(function () {
                $(this).addClass("ui-state-focus").removeClass("ui-state-hover")
            }).blur(function () {
                $(this).removeClass("ui-state-focus ui-state-hover")
            });
            return this
        }, isIE: function (c) {
            return b.env.isIE(c)
        }, info: function (c) {
            if (this.logger) {
                this.logger.info(c)
            }
        }, debug: function (c) {
            if (this.logger) {
                this.logger.debug(c)
            }
        }, warn: function (c) {
            if (this.logger) {
                this.logger.warn(c)
            }
            if (b.isDevelopmentProjectStage() && a.console) {
                console.log(c)
            }
        }, error: function (c) {
            if (this.logger) {
                this.logger.error(c)
            }
            if (b.isDevelopmentProjectStage() && a.console) {
                console.log(c)
            }
        }, isDevelopmentProjectStage: function () {
            return b.settings.projectStage === "Development"
        }, setCaretToEnd: function (d) {
            if (d) {
                d.focus();
                var e = d.value.length;
                if (e > 0) {
                    if (d.setSelectionRange) {
                        d.setSelectionRange(0, e)
                    } else {
                        if (d.createTextRange) {
                            var c = d.createTextRange();
                            c.collapse(true);
                            c.moveEnd("character", 1);
                            c.moveStart("character", 1);
                            c.select()
                        }
                    }
                }
            }
        }, changeTheme: function (g) {
            if (g && g !== "") {
                var h = $('link[href*="javax.faces.resource/theme.css"]');
                if (h.length === 0) {
                    h = $('link[href*="javax.faces.resource=theme.css"]')
                }
                var f = h.attr("href"), e = f.split("&")[0], d = e.split("ln=")[1], c = f.replace(d, "primefaces-" + g);
                h.attr("href", c)
            }
        }, escapeRegExp: function (c) {
            return this.escapeHTML(c.replace(/([.?*+^$[\]\\(){}|-])/g, "\\$1"))
        }, escapeHTML: function (c) {
            return c.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;")
        }, clearSelection: function () {
            if (a.getSelection) {
                if (a.getSelection().empty) {
                    a.getSelection().empty()
                } else {
                    if (a.getSelection().removeAllRanges) {
                        a.getSelection().removeAllRanges()
                    }
                }
            } else {
                if (document.selection && document.selection.empty) {
                    try {
                        document.selection.empty()
                    } catch (c) {
                    }
                }
            }
        }, getSelection: function () {
            var c = "";
            if (a.getSelection) {
                c = a.getSelection()
            } else {
                if (document.getSelection) {
                    c = document.getSelection()
                } else {
                    if (document.selection) {
                        c = document.selection.createRange().text
                    }
                }
            }
            return c
        }, hasSelection: function () {
            return this.getSelection().length > 0
        }, cw: function (c, f, d, e) {
            b.createWidget(c, f, d, e)
        }, createWidget: function (c, j, e, h) {
            e.widgetVar = j;
            if (b.widget[c]) {
                var g = b.widgets[j];
                if (g && (g.constructor === b.widget[c])) {
                    g.refresh(e)
                } else {
                    b.widgets[j] = new b.widget[c](e);
                    if (b.settings.legacyWidgetNamespace) {
                        a[j] = b.widgets[j]
                    }
                }
            } else {
                var f = b.getFacesResource(h + "/" + h + ".js", "primefaces");
                var i = b.getFacesResource(h + "/" + h + ".css", "primefaces");
                var d = '<link type="text/css" rel="stylesheet" href="' + i + '" />';
                $("head").append(d);
                b.getScript(f, function () {
                    setTimeout(function () {
                        b.widgets[j] = new b.widget[c](e)
                    }, 100)
                })
            }
        }, getFacesResource: function (f, e, c) {
            var d = $('script[src*="/javax.faces.resource/' + b.getCoreScriptName() + '"]').attr("src");
            if (!d) {
                d = $('script[src*="javax.faces.resource=' + b.getCoreScriptName() + '"]').attr("src")
            }
            d = d.replace(b.getCoreScriptName(), f);
            d = d.replace("ln=primefaces", "ln=" + e);
            if (c) {
                var h = new RegExp("[?&]v=([^&]*)").exec(d)[1];
                d = d.replace("v=" + h, "v=" + c)
            }
            var g = a.location.protocol + "//" + a.location.host;
            return d.indexOf(g) >= 0 ? d : g + d
        }, getCoreScriptName: function () {
            return"primefaces.js"
        }, inArray: function (c, e) {
            for (var d = 0; d < c.length; d++) {
                if (c[d] === e) {
                    return true
                }
            }
            return false
        }, isNumber: function (c) {
            return typeof c === "number" && isFinite(c)
        }, getScript: function (c, d) {
            $.ajax({type: "GET", url: c, success: d, dataType: "script", cache: true})
        }, focus: function (e, d) {
            var c = ":not(:submit):not(:button):input:visible:enabled[name]";
            setTimeout(function () {
                if (e) {
                    var f = $(b.escapeClientId(e));
                    if (f.is(c)) {
                        f.focus()
                    } else {
                        f.find(c).eq(0).focus()
                    }
                } else {
                    if (d) {
                        $(b.escapeClientId(d)).find(c).eq(0).focus()
                    } else {
                        $(c).eq(0).focus()
                    }
                }
            }, 250);
            b.customFocus = true
        }, monitorDownload: function (d, c) {
            if (this.cookiesEnabled()) {
                if (d) {
                    d()
                }
                a.downloadMonitor = setInterval(function () {
                    var e = b.getCookie("primefaces.download");
                    if (e === "true") {
                        if (c) {
                            c()
                        }
                        clearInterval(a.downloadMonitor);
                        b.setCookie("primefaces.download", null)
                    }
                }, 250)
            }
        }, scrollTo: function (d) {
            var c = $(b.escapeClientId(d)).offset();
            $("html,body").animate({scrollTop: c.top, scrollLeft: c.left}, {easing: "easeInCirc"}, 1000)
        }, scrollInView: function (d, g) {
            if (g.length === 0) {
                return
            }
            var j = parseFloat(d.css("borderTopWidth")) || 0, f = parseFloat(d.css("paddingTop")) || 0, h = g.offset().top - d.offset().top - j - f, c = d.scrollTop(), e = d.height(), i = g.outerHeight(true);
            if (h < 0) {
                d.scrollTop(c + h)
            } else {
                if ((h + i) > e) {
                    d.scrollTop(c + h - e + i)
                }
            }
        }, calculateScrollbarWidth: function () {
            if (!this.scrollbarWidth) {
                if (b.env.browser.msie) {
                    var e = $('<textarea cols="10" rows="2"></textarea>').css({position: "absolute", top: -1000, left: -1000}).appendTo("body"), d = $('<textarea cols="10" rows="2" style="overflow: hidden;"></textarea>').css({position: "absolute", top: -1000, left: -1000}).appendTo("body");
                    this.scrollbarWidth = e.width() - d.width();
                    e.add(d).remove()
                } else {
                    var c = $("<div />").css({width: 100, height: 100, overflow: "auto", position: "absolute", top: -1000, left: -1000}).prependTo("body").append("<div />").find("div").css({width: "100%", height: 200});
                    this.scrollbarWidth = 100 - c.width();
                    c.parent().remove()
                }
            }
            return this.scrollbarWidth
        }, bcn: function (d, e, g) {
            if (g) {
                for (var c = 0; c < g.length; c++) {
                    var f = g[c].call(d, e);
                    if (f === false) {
                        if (e.preventDefault) {
                            e.preventDefault()
                        } else {
                            e.returnValue = false
                        }
                        break
                    }
                }
            }
        }, bcnu: function (e, f, d) {
            if (d) {
                for (var c = 0; c < d.length; c++) {
                    var g = d[c].call(e, f);
                    if (g === false) {
                        break
                    }
                }
            }
        }, openDialog: function (c) {
            b.dialog.DialogHandler.openDialog(c)
        }, closeDialog: function (c) {
            b.dialog.DialogHandler.closeDialog(c)
        }, showMessageInDialog: function (c) {
            b.dialog.DialogHandler.showMessageInDialog(c)
        }, confirm: function (c) {
            b.dialog.DialogHandler.confirm(c)
        }, deferredRenders: [], addDeferredRender: function (e, c, d) {
            this.deferredRenders.push({widget: e, container: c, callback: d})
        }, removeDeferredRenders: function (e) {
            for (var d = (this.deferredRenders.length - 1); d >= 0; d--) {
                var c = this.deferredRenders[d];
                if (c.widget === e) {
                    this.deferredRenders.splice(d, 1)
                }
            }
        }, invokeDeferredRenders: function (c) {
            var g = [];
            for (var f = 0; f < this.deferredRenders.length; f++) {
                var d = this.deferredRenders[f];
                if (d.container === c) {
                    var h = d.callback.call();
                    if (h) {
                        g.push(d.widget)
                    }
                }
            }
            for (var e = 0; e < g.length; e++) {
                this.removeDeferredRenders(g[e])
            }
        }, zindex: 1000, customFocus: false, detachedWidgets: [], PARTIAL_REQUEST_PARAM: "javax.faces.partial.ajax", PARTIAL_UPDATE_PARAM: "javax.faces.partial.render", PARTIAL_PROCESS_PARAM: "javax.faces.partial.execute", PARTIAL_SOURCE_PARAM: "javax.faces.source", BEHAVIOR_EVENT_PARAM: "javax.faces.behavior.event", PARTIAL_EVENT_PARAM: "javax.faces.partial.event", RESET_VALUES_PARAM: "primefaces.resetvalues", IGNORE_AUTO_UPDATE_PARAM: "primefaces.ignoreautoupdate", VIEW_STATE: "javax.faces.ViewState", CLIENT_WINDOW: "javax.faces.ClientWindow", VIEW_ROOT: "javax.faces.ViewRoot", CLIENT_ID_DATA: "primefaces.clientid"};
    b.settings = {};
    b.util = {};
    b.widgets = {};
    b.locales = {en_US: {closeText: "Close", prevText: "Previous", nextText: "Next", monthNames: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"], monthNamesShort: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"], dayNames: ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"], dayNamesShort: ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"], dayNamesMin: ["S", "M", "T", "W ", "T", "F ", "S"], weekHeader: "Week", firstDay: 0, isRTL: false, showMonthAfterYear: false, yearSuffix: "", timeOnlyTitle: "Only Time", timeText: "Time", hourText: "Hour", minuteText: "Minute", secondText: "Second", currentText: "Current Date", ampm: false, month: "Month", week: "week", day: "Day", allDayText: "All Day"}};
    PF = function (d) {
        var c = b.widgets[d];
        if (!c) {
            b.error("Widget for var '" + d + "' not available!")
        }
        return c
    };
    a.PrimeFaces = b
})(window);
PrimeFaces.env = {mobile: false, touch: false, ios: false, browser: null, init: function () {
        this.mobile = /Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(window.navigator.userAgent);
        this.touch = "ontouchstart" in window || window.navigator.msMaxTouchPoints || PrimeFaces.env.mobile;
        this.ios = /iPhone|iPad|iPod/i.test(window.navigator.userAgent);
        this.resolveUserAgent()
    }, resolveUserAgent: function () {
        if ($.browser) {
            this.browser = $.browser
        } else {
            var a, d;
            jQuery.uaMatch = function (h) {
                h = h.toLowerCase();
                var g = /(opr)[\/]([\w.]+)/.exec(h) || /(chrome)[ \/]([\w.]+)/.exec(h) || /(version)[ \/]([\w.]+).*(safari)[ \/]([\w.]+)/.exec(h) || /(webkit)[ \/]([\w.]+)/.exec(h) || /(opera)(?:.*version|)[ \/]([\w.]+)/.exec(h) || /(msie) ([\w.]+)/.exec(h) || h.indexOf("trident") >= 0 && /(rv)(?::| )([\w.]+)/.exec(h) || h.indexOf("compatible") < 0 && /(mozilla)(?:.*? rv:([\w.]+)|)/.exec(h) || [];
                var f = /(ipad)/.exec(h) || /(iphone)/.exec(h) || /(android)/.exec(h) || /(windows phone)/.exec(h) || /(win)/.exec(h) || /(mac)/.exec(h) || /(linux)/.exec(h) || /(cros)/i.exec(h) || [];
                return{browser: g[3] || g[1] || "", version: g[2] || "0", platform: f[0] || ""}
            };
            a = jQuery.uaMatch(window.navigator.userAgent);
            d = {};
            if (a.browser) {
                d[a.browser] = true;
                d.version = a.version;
                d.versionNumber = parseInt(a.version)
            }
            if (a.platform) {
                d[a.platform] = true
            }
            if (d.android || d.ipad || d.iphone || d["windows phone"]) {
                d.mobile = true
            }
            if (d.cros || d.mac || d.linux || d.win) {
                d.desktop = true
            }
            if (d.chrome || d.opr || d.safari) {
                d.webkit = true
            }
            if (d.rv) {
                var e = "msie";
                a.browser = e;
                d[e] = true
            }
            if (d.opr) {
                var c = "opera";
                a.browser = c;
                d[c] = true
            }
            if (d.safari && d.android) {
                var b = "android";
                a.browser = b;
                d[b] = true
            }
            d.name = a.browser;
            d.platform = a.platform;
            this.browser = d;
            $.browser = d
        }
    }, isIE: function (a) {
        return(a === undefined) ? this.browser.msie : (this.browser.msie && parseInt(this.browser.version, 10) === a)
    }, isLtIE: function (a) {
        return(this.browser.msie) ? parseInt(this.browser.version, 10) < a : false
    }, isCanvasSupported: function () {
        var a = document.createElement("canvas");
        return !!(a.getContext && a.getContext("2d"))
    }};
