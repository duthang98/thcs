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
            $(".table__2").replaceWith(`<div class=\"table__2\"></div>`)

            $(".table__3").replaceWith(`<div class=\"table__3\"></div>`)
            for (let j = 0; j < ketqua.length; j++) {
                if(ketqua[j].length > 0){
                    let data = `<div class=\"table__${j+1}\"><table class=\"table\">\n` +
                        "      <thead>\n" +
                        "      <tr>\n" +
                        "        <th>index</th>\n" +
                        "        <th>subject</th>\n" +
                        "        <th>predict</th>\n" +
                        "        <th>object</th>\n" +
                        "      </tr>\n" +
                        "      </thead>\n" +
                        "      <tbody class=\"replace\">";
                    for (let i = 0; i < ketqua[j].length; i++) {
                        data += `<tr class="table__data"><td>${i + 1}</td><td>${ketqua[j][i].subject}</td><td>${ketqua[j][i].predict}</td><td>${ketqua[j][i].object}</td></tr>`
                    }
                    data += "</tr>\n" +
                        "      </tbody>\n" +
                        "    </table></div>"
                    $(`.table__${j + 1}`).replaceWith(data)
                }
            }
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
        let data = "<div class=\"table__1\"><table class=\"table\">\n" +
            "      <thead>\n" +
            "      <tr>\n" +
            "        <th>index</th>\n" +
            "        <th>subject</th>\n" +
            "        <th>predict</th>\n" +
            "        <th>object</th>\n" +
            "      </tr>\n" +
            "      </thead>\n" +
            "      <tbody class=\"replace\">";
        for (let i = 0; i < ketqua[0].length; i++) {
            data += `<tr class="table__data"><td>${i + 1}</td><td>${ketqua[0][i].subject}</td><td>${ketqua[0][i].predict}</td><td>${ketqua[0][i].object}</td></tr>`
        }
        data += "</tr>\n" +
            "      </tbody>\n" +
            "    </table></div>"
        $(".table__1").replaceWith(data)
        $(".table__2").replaceWith("<div class=\"table__2\"></div>")
        $(".table__3").replaceWith("<div class=\"table__3\"></div>")
    });
})
