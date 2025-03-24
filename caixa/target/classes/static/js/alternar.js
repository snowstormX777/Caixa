function alternarExtrato(){
    const checkbox = document.getElementById("check");
    const periodo = document.querySelector("periodo");
    periodo.style.display = "none";
    if(checkbox.checked){
        periodo.style.display = "flex";
    } else {
        periodo.style.display = "none";
    }
}

