function alternarExtrato(){
    const x = document.querySelector("umdia");
    const y = document.querySelector("intervalo");
    y.style.display = "none";
    if(x.style.display === "flex"){
        x.style.display = "none";
        y.style.display = "flex";
    } else {
        x.style.display = "flex";
        y.style.display = "none";
    }
}

