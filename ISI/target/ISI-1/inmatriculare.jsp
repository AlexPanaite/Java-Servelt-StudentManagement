<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.sql.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>


<%
    Gson gsonObj = new Gson();
    Map<Object,Object> map = null;
    List<Map<Object,Object>> list = new ArrayList<Map<Object,Object>>();
    String dataPoints = null;

    try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/database", "root", "");
        Statement statement1 = connection.createStatement();
        Statement statement2 = connection.createStatement();
        Statement statement3 = connection.createStatement();
        Statement statement4 = connection.createStatement();
        Statement statement5 = connection.createStatement();
        Statement statement6 = connection.createStatement();

        String m10,m910,m89,m78,m67,m56;

        ResultSet resultSet1 = statement1.executeQuery("select count(medie)as count1 from inmatriculare where medie=10");
        ResultSet resultSet2 = statement2.executeQuery("select count(medie)as count2 from inmatriculare where medie<10 and medie>=9");
        ResultSet resultSet3 = statement3.executeQuery("select count(medie)as count3 from inmatriculare where medie<9 and medie>=8");
        ResultSet resultSet4 = statement4.executeQuery("select count(medie)as count4 from inmatriculare where medie<8 and medie>=7");
        ResultSet resultSet5 = statement5.executeQuery("select count(medie)as count5 from inmatriculare where medie<7 and medie>=6");
        ResultSet resultSet6 = statement6.executeQuery("select count(medie)as count6 from inmatriculare where medie<6 and medie>=5");

        if(resultSet1.next()){
            m10 = resultSet1.getString("count1");
            map = new HashMap<Object,Object>(); map.put("label", "Media 10"); map.put("y", Double.parseDouble(m10)); list.add(map);
            dataPoints = gsonObj.toJson(list);
        }
        if(resultSet2.next()){
            m910 = resultSet2.getString("count2");
            map = new HashMap<Object,Object>(); map.put("label", "Media intre 9 si 10"); map.put("y", Double.parseDouble(m910)); list.add(map);
            dataPoints = gsonObj.toJson(list);
        }
        if(resultSet3.next()){
            m89 = resultSet3.getString("count3");
            map = new HashMap<Object,Object>(); map.put("label", "Media intre 8 si 9"); map.put("y", Double.parseDouble(m89)); list.add(map);
            dataPoints = gsonObj.toJson(list);
        }
        if(resultSet4.next()){
            m78 = resultSet4.getString("count4");
            map = new HashMap<Object,Object>(); map.put("label", "Media intre 7 si 8"); map.put("y", Double.parseDouble(m78)); list.add(map);
            dataPoints = gsonObj.toJson(list);
        }
        if(resultSet5.next()){
            m67 = resultSet5.getString("count5");
            map = new HashMap<Object,Object>(); map.put("label", "Media intre 6 si 7"); map.put("y", Double.parseDouble(m67)); list.add(map);
            dataPoints = gsonObj.toJson(list);
        }
        if(resultSet6.next()){
            m56 = resultSet6.getString("count6");
            map = new HashMap<Object,Object>(); map.put("label", "Media intre 5 si 6"); map.put("y", Double.parseDouble(m56)); list.add(map);
            dataPoints = gsonObj.toJson(list);
        }


        connection.close();
    }
    catch(SQLException e){
        out.println("<div  style='width: 50%; margin-left: auto; margin-right: auto; margin-top: 200px;'>Could not connect to the database. Please check if you have mySQL Connector installed on the machine - if not, try installing the same.</div>");
        dataPoints = null;
    }
    %>
<!DOCTYPE html>
<html>
<head>
    <title>Inmatriculare</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {
            margin: 0;
            font-family: Arial, Helvetica, sans-serif;
        }

        .topnav {
            overflow: hidden;
            background-color: #333;
        }

        .topnav-right {
            float:right;
        }

        .topnav a {
            float: left;
            color: #f2f2f2;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
            font-size: 17px;
        }

        .topnav a:hover {
            background-color: #ddd;
            color: black;
        }


        .topnav a.active {
            background-color: #4CAF50;
            color: white;
        }

        table {

            clear: left;
            border-collapse: collapse;
            width: 50%;
            color: #588c7e;
            font-family: monospace;
            font-size: 25px;
            text-align: left;
            margin-left: 80px;
            border: 1px solid #ddd;
        }
        th {
            cursor: pointer;
            background-color: #588c7e;
            color: white;
        }

        tr:nth-child(even) {background-color: #f2f2f2}

        .row{
            display:flex

        }
        .column-left {
            float: left;

        }
        .column-right {
            float: right;

            width: 350px;
            margin-right: 200px;
            margin-left:100px;
            padding: 20px;}

        .row:after {
            content: "";
            display: table;
            clear: both;}

        #myInput {
            background-image: url('https://img.icons8.com/android/24/000000/search.png'); /* Add a search icon to input */
            background-position: 5px 7px; /* Position the search icon */
            background-repeat: no-repeat; /* Do not repeat the icon image */
            width: 300px; /* Full-width */
            font-size: 16px; /* Increase font-size */
            padding: 6px 20px 6px 40px; /* Add some padding */
            border: 1px solid #ddd; /* Add a grey border */ /* Add some space below the input */
            margin-top: 30px;
            margin-bottom: 10px;
            margin-left: 80px;
            margin-right: 10px;
        }
        table tr{
            cursor: pointer;transition: all .25s ease-in-out;
        }
        table tr:hover{background-color: #ddd;}

        #filter{

            margin-top: 30px;
            margin-right: 10px;

        }
        #btnXls{
            margin-right: 10px;
        }
        #miel{
            margin-top: 10px;
        }

    </style>
</head>
<body>


<div class="topnav">
    <%
        if(session.getAttribute("login")==null)
        {
            response.sendRedirect("login.jsp");
        }
    %>
    <a href="index.jsp">Home</a>
    <a href="/student">Studenti</a>
    <a href="/catalog">Catalog</a>
    <a class="active" href="/matricol">Inmatriculare</a>
    <div id="usr">
        <a href="/userpage">Users</a>
    </div>
    <div id="log">
        <a href="/log">Logs</a>
    </div>
    <div class="topnav-right">
        <a href="reset.jsp"  >Reset Your Password</a>
        <a href="logout.jsp" >Log Out</a>
    </div>
</div>


<c:if test="${'profesor' == sessionScope.login.role}">
    <style type="text/css">
        #comenzi{
            display:none;
        }
        #usr{
            display:none;
        }
        #log{
            display:none;
        }
    </style>
</c:if>

<c:if test="${'secretar' == sessionScope.login.role}">
    <style type="text/css">
        #usr{
            display:none;
        }
        #log {
            display: none;
        }
    </style>
</c:if>

<span class="column-left" >
<input  type="text" id="myInput" onkeyup="myFunction()" placeholder="Search for ...">
        <select id="filter" name="filter" style="height:35px; width:120px" >
        <option value="0">ID</option>
        <option value="1">Nume</option>
        <option value="2">Prenume</option>
        <option value="3">An</option>
        <option value="4">Grupa</option>
        <option value="5">Medie</option>
        <option value="6">Bursa</option>
        <option value="7">FormaInv</option>
        <option value="8">Codst</option>
        <option value="9">Cods</option>
    </select>
<button class="btn btn-success" id= "btnXls" onclick="exportTableToExcel('myTable', 'Inmatriculare')" >Xls file</button>
<button class="btn btn-danger" id="btnExport" value="Export" onclick="Export()" >PDF file</button>
</span>

<table id="myTable" class="column-left" border="1">
    <tr>
        <th onclick="sortTable(0)">ID</th>
        <th onclick="sortTable(1)">Nume</th>
        <th onclick="sortTable(2)">Prenume</th>
        <th onclick="sortTable(3)">An</th>
        <th onclick="sortTable(4)">Grupa</th>
        <th onclick="sortTable(5)">Medie</th>
        <th onclick="sortTable(6)">Bursa</th>
        <th onclick="sortTable(7)">FormaInv</th>
        <th onclick="sortTable(8)">CodSt</th>
        <th onclick="sortTable(9)">CodS</th>
    </tr>
    <tbody>

    <c:forEach var="matricol" items="${listMatricol}">

        <tr>
            <td><c:out value="${matricol.codm}" /></td>
            <td><c:out value="${matricol.nume}" /></td>
            <td><c:out value="${matricol.prenume}" /></td>
            <td><c:out value="${matricol.an}" /></td>
            <td><c:out value="${matricol.grupa}" /></td>
            <td><c:out value="${matricol.medie}" /></td>
            <td><c:out value="${matricol.bursa}" /></td>
            <td><c:out value="${matricol.formainv}" /></td>
            <td><c:out value="${matricol.codst}" /></td>
            <td><c:out value="${matricol.cods}" /></td>
        </tr>
    </c:forEach>

    </tbody>

</table>


<form class="column-right" method="post" id="comenzi">

    <h2> Introduceti datele studentului mai jos </h2>
    <div>
        <label>Cod Student</label>
        <input type="text" name="codst" id="codst" class="form-control" value="<c:out value='${matricol.codst}' />">
    </div>
    <div>
        <label>ID</label>
        <input type="text" name="codm" id="id" class="form-control" value="<c:out value='${matricol.codm}' />">
        <span class="help-block"></span>
    </div>
    <div>
        <label>Cod Specializare</label>
        <input type="text" name="cods" id="cods" class="form-control" value="<c:out value='${matricol.cods}' />">
    </div>
    <div>
        <label>Nume</label>
        <input type="text" name="nume" id="nume" class="form-control" value="<c:out value='${matricol.nume}' />">
    </div>
    <div>
        <label>Prenume</label>
        <input type="text" name="prenume" id="prenume" class="form-control" value="<c:out value='${matricol.prenume}' />">
    </div>
    <div>
        <label>An</label>
        <input type="text" name="an" id="an" class="form-control" value="<c:out value='${matricol.an}' />">
    </div>
    <div>
        <label>Grupa</label>
        <input type="text" name="grupa" id="grupa" class="form-control" value="<c:out value='${matricol.grupa}' />">
    </div>
    <div>
        <label>Cetatenie</label>
        <input type="text" name="cetatenie" id="cetatenie" class="form-control" value="<c:out value='${matricol.cetatenie}' />">
    </div>
    <div>
        <label>Data nasterii</label>
        <input type="text" name="datan" id="datan" class="form-control" value="<c:out value='${matricol.datan}' />">
    </div>
    <div>
        <label>Medie</label>
        <input type="text" name="medie" id="medie" class="form-control" value="<c:out value='${matricol.medie}' />">
    </div>
    <div>
        <label>Forma de invatamant</label>
        <input type="text" name="formainv" id="formainv" class="form-control" value="<c:out value='${matricol.formainv}' />">
    </div>
    <div>
        <label>Bursa</label>
        <input type="text" name="bursa" id="bursa" class="form-control" value="<c:out value='${matricol.bursa}' />">
    </div>
    <div class="form-group" id="miel">
        <input formaction="/matricol/insert" type="submit" name = "Add" class="btn btn-primary" value="Add">
        <input formaction="/matricol/update" type="submit" name = "Modify" class="btn btn-primary" value="Modify">
        <input formaction="/matricol/delete" type="submit" name = "Delete" class="btn btn-primary" value="Delete">
        <input type="reset"  name = "Reset" class="btn btn-primary" value="Reset">
    </div>
</form>
<div class="column-left" id="chartContainer" style="height: 450px; width: 750px; margin-left: 200px; margin-top: 150px;"></div>


<script>
    function myFunction() {
        // Declare variables
        var e = document.getElementById("filter");
        var val = e.value;
        var input, filter, table, tr, td, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        table = document.getElementById("myTable");
        tr = table.getElementsByTagName("tr");

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[val];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    }
    var table = document.getElementById("myTable");

        for (var i = 1; i < table.rows.length; i++) {
            table.rows[i].onclick = function () {
                document.getElementById("codst").value = this.cells[8].innerHTML;
                document.getElementById("id").value = this.cells[0].innerHTML;
                document.getElementById("cods").value = this.cells[9].innerHTML;
                document.getElementById("nume").value = this.cells[1].innerHTML;
                document.getElementById("prenume").value = this.cells[2].innerHTML;
                document.getElementById("an").value = this.cells[3].innerHTML;
                document.getElementById("grupa").value = this.cells[4].innerHTML;
                document.getElementById("medie").value = this.cells[5].innerHTML;
                document.getElementById("formainv").value = this.cells[7].innerHTML;
                document.getElementById("bursa").value = this.cells[6].innerHTML;
            };
        }

</script>


<script>
    function sortTable(n) {
        var table, rows, switching, i, x, y, shouldSwitch, dir, switchcount = 0;
        table = document.getElementById("myTable");
        switching = true;
        // Set the sorting direction to ascending:
        dir = "asc";
        /* Make a loop that will continue until
        no switching has been done: */
        while (switching) {
            // Start by saying: no switching is done:
            switching = false;
            rows = table.rows;
            /* Loop through all table rows (except the
            first, which contains table headers): */
            for (i = 1; i < (rows.length - 1); i++) {
                // Start by saying there should be no switching:
                shouldSwitch = false;
                /* Get the two elements you want to compare,
                one from current row and one from the next: */
                x = rows[i].getElementsByTagName("TD")[n];
                y = rows[i + 1].getElementsByTagName("TD")[n];
                /* Check if the two rows should switch place,
                based on the direction, asc or desc: */
                if (dir == "asc") {
                    if (x.innerHTML.toLowerCase() > y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                } else if (dir == "desc") {
                    if (x.innerHTML.toLowerCase() < y.innerHTML.toLowerCase()) {
                        // If so, mark as a switch and break the loop:
                        shouldSwitch = true;
                        break;
                    }
                }
            }
            if (shouldSwitch) {
                /* If a switch has been marked, make the switch
                and mark that a switch has been done: */
                rows[i].parentNode.insertBefore(rows[i + 1], rows[i]);
                switching = true;
                // Each time a switch is done, increase this count by 1:
                switchcount ++;
            } else {
                /* If no switching has been done AND the direction is "asc",
                set the direction to "desc" and run the while loop again. */
                if (switchcount == 0 && dir == "asc") {
                    dir = "desc";
                    switching = true;
                }
            }
        }
    }
</script>
<script>
    function exportTableToExcel(tableID, filename = ''){
        var downloadLink;
        var dataType = 'application/vnd.ms-excel';
        var tableSelect = document.getElementById(tableID);
        var tableHTML = tableSelect.outerHTML.replace(/ /g, '%20');

        // Specify file name
        filename = filename?filename+'.xls':'excel_data.xls';

        // Create download link element
        downloadLink = document.createElement("a");

        document.body.appendChild(downloadLink);

        if(navigator.msSaveOrOpenBlob){
            var blob = new Blob(['\ufeff', tableHTML], {
                type: dataType
            });
            navigator.msSaveOrOpenBlob( blob, filename);
        }else{
            // Create a link to the file
            downloadLink.href = 'data:' + dataType + ', ' + tableHTML;

            // Setting the file name
            downloadLink.download = filename;

            //triggering the function
            downloadLink.click();
        }
    }
</script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.62/pdfmake.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.5.0-beta4/html2canvas.min.js"></script>
<script type="text/javascript">
    function Export() {
        html2canvas(document.getElementById('myTable'), {
            onrendered: function (canvas) {
                var data = canvas.toDataURL();
                var docDefinition = {
                    content: [{
                        image: data,
                        quality: 1,
                        width: 500,
                        scale: 3

                    }]
                };
                pdfMake.createPdf(docDefinition).download("Inmatriculare.pdf");
            }
        });
    }
</script>

<script>
    window.onload = function () {

        var chart = new CanvasJS.Chart("chartContainer", {
            animationEnabled: true,
            exportEnabled: true,
            title:{
                text: "Diagrama cu mediile studentilor"
            },
            data: [{
                type: "pie",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 16,
                indexLabel: "{label} - #percent%",
                dataPoints: <%out.print(dataPoints);%>
    }]
    });
        chart.render();

    }
</script>
<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</body>
</html>
