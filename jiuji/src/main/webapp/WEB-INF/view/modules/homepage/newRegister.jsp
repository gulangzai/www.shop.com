<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/view/commons/taglib.jsp" %> 
<%@ include file="/WEB-INF/view/commons/jsp/main.jspf"%>
<!DOCTYPE html> 
<html> 
<link rel="stylesheet" type="text/css" href="${ctxStatic}/css/homepage/register.css">
    <!-- Basic Page Needs
  ================================================== -->
    <meta charset="utf-8">
    <title>啾唧网注册</title>
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Mobile Specific Metas
  ================================================== -->
    <meta name="viewport" content="width=device-width, initial-sca 

    <!--[if lt IE 9]>
		<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
	<![endif]-->
</head>

<body> 
    <div class="container">
        <div class="flat-form">  
            <!--/#login.form-action-->
            <div id="register" class="form-action show" style="margin-top:100px;">
            <br>
                <h1>注册</h1>
                <p>
                                                                      加入我们，完成你的梦想
                </p>
                <form>
                    <ul>
                        <li>
                            <input type="text" placeholder="Username" />
                        </li>
                        <li>
                            <input type="password" placeholder="Password" />
                        </li>
                        <li>
                            <input type="submit" value="Sign Up" class="button" />
                        </li>
                    </ul>
                </form>
            </div> 
        </div>
    </div>
    <script class="cssdeck" src="//cdnjs.cloudflare.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script>
</body>
<script>
(function( $ ) {
	  // constants
	  var SHOW_CLASS = 'show',
	      HIDE_CLASS = 'hide',
	      ACTIVE_CLASS = 'active';
	  
	  $( '.tabs' ).on( 'click', 'li a', function(e){
	    e.preventDefault();
	    var $tab = $( this ),
	         href = $tab.attr( 'href' );
	  
	     $( '.active' ).removeClass( ACTIVE_CLASS );
	     $tab.addClass( ACTIVE_CLASS );
	  
	     $( '.show' )
	        .removeClass( SHOW_CLASS )
	        .addClass( HIDE_CLASS )
	        .hide();
	    
	      $(href)
	        .removeClass( HIDE_CLASS )
	        .addClass( SHOW_CLASS )
	        .hide()
	        .fadeIn( 550 );
	  });
	})( jQuery );
</script>
</html>


