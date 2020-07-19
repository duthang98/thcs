$(document).ready(function () {
    $("#submit").submit(function (event) {
        event.preventDefault();
        let dataSend = "";
        dataSend = $("#subject").val();
        console.log(typeof dataSend);
        $.ajax({
            url: 'http://localhost:8080/api/getAll',
            type: 'GET',
            cache: false,
            data: {
                "subject": dataSend,
            }
        }).done(function (ketqua) {
            console.log(ketqua)
            let data = "<tbody class=\"replace\">";
            for (let i = 0; i < ketqua.length; i++) {
                data += `<tr class="table__data"><td>${i + 1}</td><td>${ketqua[i].subject}</td><td>${ketqua[i].predict}</td><td>${ketqua[i].object}</td></tr>`
            }
            data += "</tbody>"
            $(".replace").replaceWith(data)
        });
    });
    $.ajax({
        url: 'http://localhost:8080/api/getAll',
        type: 'GET',
        cache: false,
        data: {
            "subject": "",
        }
    }).done(function (ketqua) {
        let data = "<tbody class=\"replace\">";
        for (let i = 0; i < ketqua.length; i++) {
            data += `<tr class="table__data"><td>${i + 1}</td><td>${ketqua[i].subject}</td><td>${ketqua[i].predict}</td><td>${ketqua[i].object}</td></tr>`
        }
        data += "</tbody>"
        $(".replace").replaceWith(data)
        console.log(ketqua)
    });
})
