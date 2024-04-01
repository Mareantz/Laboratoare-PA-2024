<!DOCTYPE html>
<html>
<head>
    <title>Repository Report</title>
</head>
<body>
<h1>Repository Report</h1>
<#list repository.documents?keys as person>
    <h2>Person ID: ${person.id()}</h2>
    <#if repository.documents[person]??>
        <#list repository.documents[person] as document>
            <p>File: ${document.filePath()}</p>
        </#list>
    <#else>
        <p>No documents found for this person.</p>
/    </#if>
</#list>
</body>
</html>