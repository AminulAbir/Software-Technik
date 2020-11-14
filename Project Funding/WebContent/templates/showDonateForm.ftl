<#include "header.ftl">

<b>Welcome to our little demonstration on the PF Webapp</b><br><br>

<form method="POST" action="SupporterGUI?action=donateForProject">
	<fieldset id="searchProject">
		<legend>Required Information</legend>
		<div>
			<label>Supporeter Email</label>
			<input type="text" name="Email">
	    </div>

		<div>
			<label>Supporter PaymentInformation</label>
			<input type="text" name="PaymentInformation">
	    </div>
		<div>
			<label>Amount</label>
			<input type="text" name="Amount">
	    </div>
	</fieldset>
	<input type="hidden" value="${Pid}" name="Pid">
	<button type="submit" id="submit">Submit</button>
</form>
<#include "footer.ftl">