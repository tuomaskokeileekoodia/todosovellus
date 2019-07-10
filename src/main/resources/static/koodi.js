// Lähteenä https://medium.com/codingthesmartway-com-blog/getting-started-with-axios-166cb0035237
var task = [];
document.addEventListener("DOMContentLoaded", performGetRequest1);

// function init(){
//     performGetRequest1();
// }

function suorita(task) {
var todolista = document.getElementById("todotehtavat");
todolista.innerHTML = "<tr>" + "<th>" + "ID" +"<th>" + "TEHTAVA" + "<th>" + "VASTUUHENKILO" + "</tr>"
for (var i=0; i < task.length; i++) {
    var yksiTehtava = task[i];
    console.dir(yksiTehtava);
    todolista.innerHTML += "<tr>" + "<td>" + yksiTehtava.id + "<td>" + yksiTehtava.tehtava + "<td>" + yksiTehtava.vastuuhenkilo + "</td>"
    }
}


function performGetRequest1() {
    var resultElement = document.getElementById('todotehtavat');
    resultElement.innerHTML = '';

    axios.get('/api/todonettisivu/')
        .then(function (response) {
            resultElement.innerHTML = generateSuccessHTMLOutput(response);
            task = response.data;
            suorita(task);
        })
        .catch(function (error) {
            console.log(error);
            resultElement.innerHTML = generateErrorHTMLOutput(error);
        });
}

//Palauta yksi Todo-tehtävä ID:n perusteella
//Ei toimi tällä hetkellä
function performGetRequestbyID() {
    var resultElement = document.getElementById('getResult2');
    var todoId = document.getElementById('todoId').value;
    resultElement.innerHTML = '';

    axios.get('/api/todonettisivu/'+todoId, {
        params: {
            id: todoId
        }
    })
        .then(function (response) {
            console.log(response);
            resultElement.innerHTML = generateSuccessHTMLOutput(response);
        })
        .catch(function (error) {
            resultElement.innerHTML = generateErrorHTMLOutput(error);
        });
}

//Lisää uusi tehtävä Todo-listaan syöttämällä tehtävä tekstikenttään
//document.getElementById('todoInputForm').addEventListener('submit', performPostRequest);
function performPostRequest(e) {
    var resultElement = document.getElementById('postResult');
    var todoTitle = document.getElementById('todoTitle').value;
    resultElement.innerHTML = '';

    axios.post('/api/todonettisivu', {
            tehtava: todoTitle
    })
        .then(function (response) {
            resultElement.innerHTML = generateSuccessHTMLOutput(response);
            console.log("Tehtävän luonti onnistui");
            // performGetRequest1();
        })
        .catch(function (error) {
         resultElement.innerHTML = generateErrorHTMLOutput(error);
        });
   // e.preventDefault();
}

//Poista Todo ID:n perusteella
function performDeleteRequest(){
    var resultElement = document.getElementById('deleteResult');
    var todoIdPoistettava = document.getElementById('todoIdPoistettava').value;
    resultElement.innerHTML = '';

    axios.delete('/api/todonettisivu/'+todoIdPoistettava, {
        params: {
            id: todoIdPoistettava
        }
    })
        .then(function (response)  {
            resultElement.innerHTML = generateSuccessHTMLOutput(response);
            console.log("Tehtävän poistaminen onnistui")
            performGetRequest1();
        })
        .catch(function (error) {
            resultElement.innerHTML = generateErrorHTMLOutput(error);
        });
 //   e.preventDefault();
}

function generateSuccessHTMLOutput(response) {
    return  '<h4>Result</h4>' +
        '<h5>Status:</h5> ' +
        '<pre>' + response.status + ' ' + response.statusText + '</pre>' +
        '<h5>Headers:</h5>' +
        '<pre>' + JSON.stringify(response.headers, null, '\t') + '</pre>' +
        '<h5>Data:</h5>' +
        '<pre>' + JSON.stringify(response.data, null, '\t') + '</pre>';
}

function generateErrorHTMLOutput(error) {
    return  '<h4>Result</h4>' +
        '<h5>Message:</h5> ' +
        '<pre>' + error.message + '</pre>' +
        '<h5>Status:</h5> ' +
        '<pre>' + error.response.status + ' ' + error.response.statusText + '</pre>' +
        '<h5>Headers:</h5>' +
        '<pre>' + JSON.stringify(error.response.headers, null, '\t') + '</pre>' +
        '<h5>Data:</h5>' +
        '<pre>' + JSON.stringify(error.response.data, null, '\t') + '</pre>';
}