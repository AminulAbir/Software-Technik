<#include "header.ftl">
<b>Welcome to our little demonstration on the PF Webapp</b><br><br>

<form method="POST" action="SupporterGUI?action=searchProject">
	<fieldset id="searchProject">
		<legend>Required Information</legend>
		<div>
			<label>Project Name</label>
			<input type="text" name="PName" >
	    </div>
	</fieldset>
	<button type="submit" id="SelectPWebpage" name="SelectPWebpage" value="Submit">Submit!</button>
</form>
<#include "footer.ftl">