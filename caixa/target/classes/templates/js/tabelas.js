function alternarTabela(){
    const x = document.querySelector("tmovimento");
    const y = document.querySelector("registramov");
    y.style.display = "none";
    if(x.style.display === "flex"){
        x.style.display = "none";
        y.style.display = "flex";
    } else {
        x.style.display = "flex";
        y.style.display = "none";
    }
    document.getElementById('data').valueAsDate = new Date();
}

