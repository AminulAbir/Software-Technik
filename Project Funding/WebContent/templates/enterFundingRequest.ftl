<#include "header.ftl">
 <strong>Welcome to our little demonstration on the PF Webapp</strong><br /><br /></p>
<form action="psgui?action=createProject" method="POST"><fieldset id="createProject"><legend>Required Information</legend>
<div><label>Project Name</label> <input  name="Project Name" type="text" /></div>
<div><label>Email</label> <input  name="Email" type="email" /></div>
<div><label>Payment Info</label> <input name="PaymentInfo" type="text" /></div>
<div><label>Description</label> <input name="Description" type="textarea" /></div>
<div><label>List of Rewards</label> <input name="ListofRewards" type="text" /></div>
<div><label>Funding Limit</label> <input name="FundingLimit" type="text" /></div>
<div><label>EndDate</label> <input id="datepicker1" name="EndDate" type="text" /></div>
</fieldset><button id="submit" type="submit">Submit</button></form>
<#include "footer.ftl">