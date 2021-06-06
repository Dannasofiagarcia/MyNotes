const nombre = document.getElementById('nombre');
const descripcion = document.getElementById('descripcion');
const toDoContainer = document.getElementById('toDoContainer');
const doingContainer = document.getElementById('doingContainer');
const doneContainer = document.getElementById('doneContainer');
const regBtn = document.getElementById('regBtn');

const register = () => {
    let obj = {
        id : 0,
        nombre : nombre.value,
        descripcion : descripcion.value
    };
    let xhr = new XMLHttpRequest();
    xhr.addEventListener('readystatechange', ()=>{
        if(xhr.readyState === 4){
            console.log(xhr.responseText);
            getAllToDo();
            getAllDoing();
            getAllDone();
        }
    });
    xhr.open('POST', 'http://localhost:8080/ParcialPR_war/api/toDo/create');
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send(JSON.stringify(obj));
};

    regBtn.addEventListener('click', register);   
    
    const getAllToDo = () =>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                let json = xhr.responseText;
                let response = JSON.parse(json);
                console.log(response);
                toDoContainer.innerHTML = '';
                for(let i = 0; i<response.length;i++){
                    let toDoObj = response[i];
                    let view = new toDoView(toDoObj);
                    view.onDeleteFinish = () =>{
                        toDoContainer.removeChild(document.getElementById('toDoComponent' + toDoObj.id));
                    };
                    view.onForwardFinish = () => {
                       getAllDoing();
                    };
                    toDoContainer.appendChild(view.render());
                }
            }
        });
        xhr.open('GET', 'http://localhost:8080/ParcialPR_war/api/toDo/all');
        xhr.send();
    };
    getAllToDo();

    const getAllDoing = () =>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                let json = xhr.responseText;
                let response = JSON.parse(json);
                console.log(response);
                doingContainer.innerHTML = '';
                for(let i = 0; i<response.length;i++){
                    let doingObj = response[i];
                    let view = new doingView(doingObj);
                    view.onDeleteFinish = () =>{
                        doingContainer.removeChild(document.getElementById('doingComponent'+doingObj.id))
                    };
                    view.onForwardFinish = () => {
                        getAllDone();
                     };
                    view.onBackwardFinish = () => {
                        getAllToDo();
                     };
                    doingContainer.appendChild(view.render());
                }
            }
        });
        xhr.open('GET', 'http://localhost:8080/ParcialPR_war/api/doing/all');
        xhr.send();
    };
    getAllDoing();

    const getAllDone = () =>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                let json = xhr.responseText;
                let response = JSON.parse(json);
                console.log(response);
                doneContainer.innerHTML = '';
                for(let i = 0; i<response.length;i++){
                    let doneObj = response[i];
                    let view = new doneView(doneObj);
                    view.onDeleteFinish = () =>{
                        doneContainer.removeChild(document.getElementById('doneComponent'+doneObj.id))
                    };
                    view.onBackwardFinish = () => {
                        getAllDoing();
                     };
                    doneContainer.appendChild(view.render());
                }
            }
        });
        xhr.open('GET', 'http://localhost:8080/ParcialPR_war/api/done/all');
        xhr.send();
    };
    getAllDone();
    