class doneView{

    constructor(note){
        this.note = note;
        this.onDeleteFinish = null;
        this.onForwardFinish = null;
        this.onBackwardFinish = null;
    }

    deleteNote = () =>{
        let xhr = new XMLHttpRequest();
        xhr.addEventListener('readystatechange', ()=>{
            if(xhr.readyState == 4){
                console.log(xhr.responseText);
                if(this.onDeleteFinish!=null){
                    this.onDeleteFinish();
                }
            }
        });
        xhr.open('DELETE', 'http://localhost:8080/ParcialPR_war/api/done/delete/'+this.note.id);
        xhr.send();
    }

    backState = () =>{
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
                if(this.onBackwardFinish!=null){
                    this.onBackwardFinish();
                }
            }
        });
        xhr.open('POST', 'http://localhost:8080/ParcialPR_war/api/doing/create');
        xhr.setRequestHeader('Content-Type','application/json');
        xhr.send(JSON.stringify(obj));
        this.deleteNote();
    }

    render = () =>{
        let component = document.createElement('div'); //<div></div>
        component.id = 'doneComponent' + this.note.id;
        component.className = 'notesClass';
        let nombre = document.createElement('p'); //<p></p>
        nombre.className = 'nombreNota'; 
        let descripcion = document.createElement('small'); //<small></small>
        descripcion.className = 'descripcionNota';
        let fecha = document.createElement('p'); //<small></small>
        descripcion.className = 'fechaNota';

        let delBtn = document.createElement('button');
        delBtn.innerHTML = ' ';
        delBtn.className = 'delBtn';

        let backBtn = document.createElement('button');
        backBtn.className = 'backBtn';
        backBtn.innerHTML = ' ';
        
        nombre.innerHTML = this.note.nombre; //<p>Nota 1</p>
        descripcion.innerHTML = this.note.descripcion;
        fecha.innerHTML = this.note.fecha;


        component.appendChild(nombre); //<div><p></p></div>
        component.appendChild(descripcion); //div<p></p><small></small></div>
        component.appendChild(fecha);
        component.appendChild(delBtn);
        component.appendChild(backBtn);

        
        delBtn.addEventListener('click', this.deleteNote);
        backBtn.addEventListener('click', this.backState);

        return component;
    }

}