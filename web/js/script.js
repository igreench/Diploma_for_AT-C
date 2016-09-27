/**
 * Created by Olcha on 13.06.2016.
 */
$(document).ready(function(){
    $('#attachDocumentsTable').DataTable({
        language:{
            "processing": "Подождите...",
            "search": "Поиск:",
            "lengthMenu": "Показать _MENU_ записей",
            "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
            "infoEmpty": "Записи с 0 до 0 из 0 записей",
            "infoFiltered": "(отфильтровано из _MAX_ записей)",
            "infoPostFix": "",
            "loadingRecords": "Загрузка записей...",
            "zeroRecords": "Записи отсутствуют.",
            "emptyTable": "В таблице отсутствуют данные",
            "paginate": {
                "first": "Первая",
                "previous": "Предыдущая",
                "next": "Следующая",
                "last": "Последняя"
            },
            "aria": {
                "sortAscending": ": активировать для сортировки столбца по возрастанию",
                "sortDescending": ": активировать для сортировки столбца по убыванию"
            }
        },
        columns: [
            { title: "Вид документа" },
            { title: "Наименование" },
            { title: "Серия" },
            { title: "Номер" },
            { title: "Кем выдан" },
            { title: "Дата выдачи" },
            { title: "Файл" },
            { title: "Файл ЭЦП" }
        ]
    });
    $('#historyTable').DataTable({
        // data: dataSet,
        language:{
            "processing": "Подождите...",
            "search": "Поиск:",
            "lengthMenu": "Показать _MENU_ записей",
            "info": "Записи с _START_ до _END_ из _TOTAL_ записей",
            "infoEmpty": "Записи с 0 до 0 из 0 записей",
            "infoFiltered": "(отфильтровано из _MAX_ записей)",
            "infoPostFix": "",
            "loadingRecords": "Загрузка записей...",
            "zeroRecords": "Записи отсутствуют.",
            "emptyTable": "В таблице отсутствуют данные",
            "paginate": {
                "first": "Первая",
                "previous": "Предыдущая",
                "next": "Следующая",
                "last": "Последняя"
            },
            "aria": {
                "sortAscending": ": активировать для сортировки столбца по возрастанию",
                "sortDescending": ": активировать для сортировки столбца по убыванию"
            }
        },
        columns: [
            { title: "№" },
            { title: "Номер заявки" },
            { title: "GUID" },
            { title: "Дата отправления" },
            { title: "Статус заявки" }
        ]
    });
});

function addDoc() {
    var appDocType = $("#appDocType").val();
    var appliedDocumentName = $("#appliedDocumentName").val();
    var appDocSerial = $("#appDocSerial").val();
    var appDocNumber = $("#appDocNumber").val();
    var appDocIssueOrgan = $("#appDocIssueOrgan").val();
    var appDate = $("#appDate").val();
    var appDocLoader = document.getElementById("appDocLoader").value;
    var appDocSignLoader = document.getElementById("appDocSignLoader").value;
    $('#attachDocumentsTable').DataTable().row.add([appDocType,appliedDocumentName,appDocSerial,appDocNumber,appDocIssueOrgan ,appDate,appDocLoader,appDocSignLoader]).draw();
}

function processFiles(files) {
    console.log(files);
}

// function hello(){
//     var ajax = new XMLHttpRequest();
//     ajax.open('POST', 'http://localhost:8080/request', false);
//     ajax.send(JSON.stringify(data));
//     var yyy =  ajax.responseText;
//     alert(yyy);
// }



function sendSOAP() { 
    var ajax = new XMLHttpRequest();
    ajax.open('POST', 'http://localhost:8080/request', false);
    var array = [];
    debugger;
    var appDocArray = [{}];
    for (z = 0; z < $("#attachDocumentsTable").DataTable().rows().count(); z++ ){
        appDocArray.push({
            "appDocType" : $('#attachDocumentsTable').DataTable().cell(z,0).data(),
            "appliedDocumentName" : $('#attachDocumentsTable').DataTable().cell(z,1).data(),
            "appDocSerial" : $('#attachDocumentsTable').DataTable().cell(z,2).data(),
            "appDocNumber" : $('#attachDocumentsTable').DataTable().cell(z,3).data(),
            "appDocIssueOrgan" : $('#attachDocumentsTable').DataTable().cell(z,4).data(),
            "appDate" : $('#attachDocumentsTable').DataTable().cell(z,5).data(),
            "appDocLoader" : $('#attachDocumentsTable').DataTable().cell(z,6).data(),
            "appDocSignLoader" : $('#attachDocumentsTable').DataTable().cell(z,7).data()
        });
        // appDocArray[z].appDocType = $('#attachDocumentsTable').DataTable().cell(z,0).data();
        // appDocArray[z].appliedDocumentName = $('#attachDocumentsTable').DataTable().cell(z,1).data();
        // appDocArray[z].appDocSerial = $('#attachDocumentsTable').DataTable().cell(z,2).data();
        // appDocArray[z].appDocNumber = $('#attachDocumentsTable').DataTable().cell(z,3).data();
        // appDocArray[z].appDocIssueOrgan = $('#attachDocumentsTable').DataTable().cell(z,4).data();
        // appDocArray[z].appDate = $('#attachDocumentsTable').DataTable().cell(z,5).data();
        // appDocArray[z].appDocLoader = $('#attachDocumentsTable').DataTable().cell(z,6).data();
        // appDocArray[z].appDocSignLoader = $('#attachDocumentsTable').DataTable().cell(z,7).data();
    }

    array[0] = objectTypes[$("#objTypeSelect").val()];
    array[1] = areaMeasureTypes[$("#areaMeasureSelect").val()];
    array[2] = $("#areaSize").val();
    array[3] = regions[$("#reqionSelect").val()];
    array[4] = cityTypes[$("#citySelect").val()];
    array[5] = $("#nameOfSettlement").val();
    array[6] = streetTypes[$("#streetSelect").val()];
    array[7] = $("#streetName").val();
    array[8] = l1Types[$("#houseSelect").val()];
    array[9] = $("#l1value").val();
    array[10] = l2Types[$("#buildingSelect").val()];
    array[11] = $("#l2value").val();
    array[12] = l3Types[$("#housingSelect").val()];
    array[13] = $("#l3value").val();
    array[14] = apartmentTypes[$("#flatSelect").val()];
    array[15] = $("#apartmentNumberObject").val();
    array[16] = $("#otherLocationObject").val();
    array[17] = governanceNames[$("#declarantNameSelect").val()];
    array[18] = $("#govMail").val();
    array[19] = agentTypes[$("#agencyDeclarantCategorySelect").val()];
    array[20] = $("#secondName").val();
    array[21] = $("#firstName").val();
    array[22] = $("#patronymic").val();
    array[23] = $("#SNILS").val();
    array[24] = documentsCode[$("#agencyDocumentTypeSelect").val()];
    array[25] = $("#passportSerial").val();
    array[26] = $("#passportNumber").val();
    array[27] = $("#passportGiver").val();
    array[28] = $("#passportDate").val();
    array[29] = $("#agencyMail").val();
    array[30] = $("#agencyPhone").val();
    array[31] = $("#agencyInfo").val();
    array[32] = $("input[name=rgroup1]:checked").val();
    array[33] = $("#postalCode").val();
    array[34] = regions[$("#agencyRegion").val()];
    array[35] = cityTypes[$("#agencyCity").val()];
    array[36] = $("#agencySettleName").val();
    array[37] = streetTypes[$("#agencyStreet").val()];
    array[38] = $("#agencyStreetValue").val();
    array[39] = l1Types[$("#agencyL1TypeSelect").val()];
    array[40] = $("#agencyl1value").val();
    array[41] = l2Types[$("#agencyL2TypeSelect").val()];
    array[42] = $("#agencyl2value").val();
    array[43] = l3Types[$("#agencyL3TypeSelect").val()];
    array[44] = $("#agencyl3value").val();
    array[45] = apartmentTypes[$("#agencyApartmentTypeSelect").val()];
    array[46] = $("#agencyRoomNumber").val();
    array[47] = $("#other").val();
    array[48] = $("#otherLocation").val();
    array[49] = $("#agencyL2TypeSelect").val();
    array[50] = appDocArray;
    ajax.send(JSON.stringify(array));
    var yyy =  ajax.responseText;
}

var regions = ["Калужский","30","50"];
var objectTypes = ["IsNondomestic", "Parcel", "IsFlat", "IsRoom", "002001005000", "IsNondomestic",
    "002001006000", "002001004000", "002001007000"];
var areaMeasureTypes = [100.0, 200.0, 300.0];
var cityTypes = ["г"];
var streetTypes = ["ул"];
var l1Types = ["дом"];
var l2Types = ["корп"];
var l3Types = ["литера"];
var apartmentTypes = ["к"];
var governanceNames = ["Пенсионный фонд"];
var governanceCodes= ["007001001001"];
var documentsCode = ["008001001000"];
var agentTypes = ["356003000000"];

