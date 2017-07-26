## freemarker 宏定义
    
> [marco官方文档](http://freemarker.org/docs/ref_directive_macro.html)

[freemarker宏定义语法](http://blog.chinaunix.net/uid-725717-id-2060340.html)
宏定义的用途之一：ajax分页
定义引入一次，可以多次使用j。如：<@test >


```
//: paginateAjax.html
//  分页模板
<#macro paginateAjax currentPage totalPage actionUrl pageNum=5 container="tbodyListChoice" urlParas="">
	
	<#if (totalPage <= 0) || (currentPage > totalPage)><#return></#if>
	<#local startPage = currentPage - (pageNum-1)/2>
	<#if (startPage < 1)>
		<#local startPage = 1>
	</#if>
	
	<#local endPage = currentPage + (pageNum-1)/2>
	<#if (endPage > totalPage)>
		<#local endPage = totalPage>
	</#if>
	
	<ul class="pagination pagination-sm no-margin pull-right">
			<#if (currentPage <= pageNum)>
				<#local startPage = 1> 	
				<#local endPage = pageNum>
			</#if>
			<#if ((totalPage - currentPage) < pageNum)>
				<#local startPage =(totalPage - pageNum + 1)>
				<#local endPage = totalPage>
			</#if>
			
			<li><a href="javascript:void(0)" onclick="ajax_page_${container}('${actionUrl}1${urlParas!}')">&lt;&lt;</a></li>
			<#if (currentPage == 1)>
				 <li><a href="javascript:void(0)">&lt;</a></li>
			<#else>
				 <li><a href="javascript:void(0)" onclick="ajax_page_${container}('${actionUrl}#{currentPage - 1}${urlParas!}')" >&lt;</a></li>
			</#if>
			
			<#list startPage..endPage as i>
				<#if currentPage == i>
				      <li class="active"><a href="javascript:void(0)" >${(i<10)?string('0'+i,i)}</a></li>
				<#else>  
					  <li><a href="javascript:void(0)" onclick="ajax_page_${container}('${actionUrl}#{i}${urlParas!}')">${(i<10)?string('0'+i,i)}</a></li>
				</#if>
			</#list>
			
			<#if (currentPage == totalPage)>
				 <li><a href="javascript:void(0)">&gt;</a></li>
			<#else>
				 <li><a href="javascript:void(0)" onclick="ajax_page_${container}('${actionUrl}#{currentPage + 1}${urlParas!}')">&gt;</a></li>
			</#if>
			 <li><a href="javascript:void(0)" onclick="ajax_page_${container}('${actionUrl}#{totalPage}${urlParas!}')">&gt;&gt;</a></li>
	</ul>

	<script type="text/javascript">
	 function ajax_page_${container}(url){
		 $.post(url, {
			}, function(response) {
				$("#${container!}").html(response);
			});
	 }
	</script>
</#macro> ///:~
```
```
使用:
 <#include "/common/paginateAjax.html" />
	<@paginateAjax currentPage=pageData.getCurrent() totalPage=pageData.getPages() actionUrl="/team/teamInfo/append/" urlParas="?clinicName1=${clinicName1!}&userName1=${userName1!}"  />
```
