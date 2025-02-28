function alternarTabela(){
    const x = document.querySelector("tmovimento");
    const y = document.querySelector("registramov");
    y.style.display = "none";
    if(x.style.display === "table"){
        x.style.display = "none";
        y.style.display = "block";
    } else {
        x.style.display = "table";
        y.style.display = "none";
    }
}
