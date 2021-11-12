const inputBox = document.getElementById("inputBox");
const addBtn = document.getElementById("addBtn");
const toDoList = document.getElementById("List");

addBtn.addEventListener("click", function(){
    const list = document.createElement("li");

    // 리스트 생성
    list.innerText = inputBox.value;
    toDoList.appendChild(list);
    // input 태그 공백으로
    inputBox.value = " ";
    // 리스트 완료
    list.addEventListener("click", function(){
        list.style.textDecoration = "line-through";
    })

    // 리스트 삭제
    list.addEventListener("dblclick", function(){
        toDoList.removeChild(list);
    })


})