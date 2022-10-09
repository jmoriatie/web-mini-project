// const serverAddress = 'http://localhost:8080/v1/item-api/items';

// JSON -> this page id 값에 append(HTML 태그와 함께)
function getItems() {
    $.ajax({
        method: "get",
        url: "/v1/item-api/items",
        dataType: "json"
    }).done(
        function (data) {
            console.log(data);
            data.forEach(item => {
                // TODO: thymeleaf 의 문법을 활용할 방법 알아보기
                $('#t_view').append(
                    `
                    <tr>
                        <td><a href='/item/item?search-item=${item.id}'>${item.itemName}</a></td>
                        <td>${item.price}</td>
                        <td>${item.quantity}</td>
                    </tr>
                    `
                );
            })
        }).fail(function () {
        alert("오류");
    });
}

// 그냥 Controller 의 Model 객체
// ajax -> Controller -> return 삽입할 html page(with Model) -> replaceWith -> this page 해당 id 에 삽입
// ㄴ 장점은 Thymeleaf 가 렌더링 된 값을 삽입 가능
function getItemsModel() {
    const inputData = $('#input-text').val();
    $.ajax({
        method: "post",
        url: "/item/item-test",
        data:{"keyword": inputData},
        dataType: "html"
    }).done(
        function (itemList){
            console.log(itemList);
            $('#t_view-model').replaceWith(itemList);
        }
    )
}


function test(test) {
    alert("this is test : " + test);
}

function showAlert() {
    alert("The button was clicked!");
}