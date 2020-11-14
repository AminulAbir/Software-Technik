<!DOCTYPE HTML>
<html lang='de' dir='ltr'>
<head>
	<meta charset="utf-8" />
	<title>ProjectFunding - ${pagetitle}</title>
	<link type="text/css" href="css/style.css" rel="stylesheet" media="screen" />
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" />
  	<script src="//code.jquery.com/jquery-1.10.2.js"></script>
  	<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  	<script>
  		$(function() {
    		$( "#datepicker2" ).datepicker(
    		{
    			minDate:'today',
    			
    		});
 
  			$("#datepicker1").datepicker({
  				minDate:'today',
    			onSelect: function (dateValue, inst) {
        			$("#datepicker2").datepicker("option", "minDate", dateValue)
    			}
			});
		});
  	</script>
</head>
<body>
<div id="wrapper">
	<div id="logo">Project Funding<br>Software Engineering Example</div>
    <ul id="navigation">
    	<li><a href="index" title="Index">View Homesite</a></li>
	<#if navtype == "ProjectStarter">
    	<li><a href="psgui?page=defaultwebpage" title="Create Project">Create Project</a></li>	
	<#elseif navtype == "Supporter">
		<li><a href="supportergui?page=donate" title="Donate Project">Donate Project</a></li>
	<#else>
    	<li><a href="supportergui" title="Supporter">Supporter</a></li>
		<li><a href="psgui" title="ProjectStarter">ProjectStarter</a></li>
	</#if>
    </ul>
	<div id="content">