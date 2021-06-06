class toDoView{

    constructor(note){
        this.note = note;
        this.onDeleteFinish = null;
        this.onForwardFinish = null;
    }

    deleteNote = () =>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                console.log(xhr.responseText);
                if(this.onDeleteFinish!=null){
                    this.onDeleteFinish();
                }
            }
        });
        xhr.open('DELETE', 'http://localhost:8080/ParcialPR_war/api/toDo/delete/'+this.note.id);
        xhr.send();
    }

    //
    // nextState = () =>{
    //     let obj = {
    //         id: 0,
    //         nombre: this.note.nombre,
    //         description: this.note.description
    //     };
    //     let xhr = new XMLHttpRequest();
    //     xhr.addEventListener('readystatechange', ()=>{
    //         if(xhr.readyState === 4){
    //             console.log("Agregado");
    //             //this.deleteNote();
    //             //if(this.onForwardFinish!=null){
    //             //    this.onForwardFinish();
    //             //}
    //         }
    //     });
    //     xhr.open('POST', 'http://localhost:8080/ParcialPR_war/api/doing/create');
    //     xhr.setRequestHeader('Content-Type', 'application/json');
    //     xhr.send(JSON.stringify(obj));
    // }

    nextState = () =>{
        let obj = {
            id:0,
            nombre: this.note.nombre,
            descripcion: this.note.descripcion
        };
        console.log(JSON.stringify(obj));
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState === 4){
                console.log(xhr.responseText);
                this.deleteNote();
                if(this.onForwardFinish!=null){
                    this.onForwardFinish();
                }
            }
        });
        xhr.open('POST', 'http://localhost:8080/ParcialPR_war/api/doing/create');
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.send(JSON.stringify(obj));

    }




    render = () =>{
        let component = document.createElement('div'); //<div></div>
        component.id = 'toDoComponent' + this.note.id;
        component.className = 'notesClass';
        let nombre = document.createElement('p'); //<p></p>
        nombre.className = 'nombreNota'; 
        let descripcion = document.createElement('small'); //<small></small>
        descripcion.className = 'descripcionNota';
        let fecha = document.createElement('p'); //<small></small>
        fecha.className = 'fechaNota';


        let delBtn = document.createElement('button');
        delBtn.innerHTML = ' ';
        delBtn.className = 'delBtn';

        let advanceBtn = document.createElement('button');
        advanceBtn.className = 'advanceBtn';
        advanceBtn.innerHTML = ' ';
        
        nombre.innerHTML = this.note.nombre; //<p>Nota 1</p>
        descripcion.innerHTML = this.note.descripcion;
        fecha.innerHTML = this.note.fecha;

        component.appendChild(nombre); //<div><p></p></div>
        component.appendChild(descripcion); //div<p></p><small></small></div>
        component.appendChild(fecha);
        component.appendChild(delBtn);
        component.appendChild(advanceBtn);

        
        delBtn.addEventListener('click', this.deleteNote);
        advanceBtn.addEventListener('click', this.nextState);

        return component;
    }

}