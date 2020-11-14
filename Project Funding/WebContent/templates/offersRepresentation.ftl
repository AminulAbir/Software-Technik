<#include "header.ftl">

<b>Welcome to our little demonstration on the PF Webapp</b>

<table id="availablePs">
	<tr>
		<th>#</th>
		<th>Name</th>
		<th>Description</th>
		<th>Status</th>
		<th>End Date</th>
		<th>Funding Limit</th>
	</tr>
	<#list searchedProjects as p>
	<tr>
		<td><a href="SupporterGUI?action=selectProject&amp;Pid=${p.id}" title="Make Donation">${p.id}</a></td>
		<td>${p.PName}</td>
		<td>${p.Pdescription}</td>
		<td>${p.PType.Pstatus}</td>
		<td>${p.TimeData.EndDate}</td>
		<td>${p.FundingLimit}</td>
	</tr>
	</#list>
</table>
<#include "footer.ftl">