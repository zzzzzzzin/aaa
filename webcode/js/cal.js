      //8월 달력 생성 코드 작성(브라우저 콘솔에)
      function view_month(mon) {
        console.log("\t\t2022년 "+mon+"월");
        console.log("일\t월\t화\t수\t목\t금\t토");
        const spaces = [6,2,2,5,0,3,5,1,4,6,2,4];
        const last_days=[31,28,31,30,31,30,31,31,30,31,30,31];
        let day=1;
        let week="";
        let space = spaces[mon-1];
        let last_day = last_days[mon-1];
        for(let i=0;i<space;i++) {
           week=week+"\t";
        }
        for (let i=0;i<last_day;i++) {
            week=week+day+"\t";
            if((space+day)%7===0){
                console.log(week);
                week="";
            }
            day=day+1;
        } 
        console.log(week);
    }

          //Date 클래스를 이용하여 달력을 완성하시오
          function view_monthV2(year, mon) {
            console.log("\t\t"+year+"년 "+mon+"월");
            console.log("일\t월\t화\t수\t목\t금\t토");
            let day=1;
            let week="";
            const d1 = new Date(year,mon-1,1);
            const d2 = new Date(year,mon,0);
            let space = d1.getDay();
            let last_day = d2.getDate();
            for(let i=0;i<space;i++) {
               week=week+"\t";
            }
            for (let i=0;i<last_day;i++) {
                week=week+day+"\t";
                if((space+day)%7===0){
                    console.log(week);
                    week="";
                }
                day=day+1;
            } 
            console.log(week);
            console.log("\n")
        }

        function view_month_auto(mon){
            // console.log("\t\t\t"+mon+"월");
            // console.log("일\t월\t화\t수\t목\t금\t토");
            const spaces = [6,2,2,5,0,3,5,1,4,6,2,4];
            const last_days=[31,28,31,30,31,30,31,31,30,31,30,31];
            let space = spaces[mon-1];
            let last_day = last_days[mon-1];
            let day = 1;
            let week = [];
            let calendar = "<table><td colspan='7'>"+mon+"</td></tr>";
            calendar += make_week(["일","월","화","수","목","금","토"]);
            for(let i=0;i<space;i++){
                week.push("");
            }
            for(let i=0;i<last_day;i++){
                week.push(day);
                if((space+day)%7===0){
                    calendar += make_week(week);
                    week = [];
                }
                day = day + 1;
            }
            calendar += make_week(week);
            calendar += "</table>";

            return calendar;
	    }
        function make_week(array){
            let week = "<tr><td class='red'>"+array[0]+"</td>";
            for(let i=1;i<6;i++) {
                //앞에 typeof를 붙여야 undefined를 문자로 인식해서 비교가능함
                if (typeof array[i]=='undefined') {
                    array[i]="";
                }
                week += "<td>"+array[i]+"</td>";
                
            }
            console.log(week);
            //삼항연산자
            array[6]=typeof array[6]=='undefined'?"":array[6];
            week += "<td class='blue'>"+array[6]+"</td></tr>";
            return week;
        }

        function make_calendar(){
            //month의 값을 셋팅하는 코드
            let month = document.getElementById("month").value;
            let result = view_month_auto(month);
            document.getElementById('calendar').innerHTML = result;
          }

        function make_calendar_old() {
            //month의 값을 세팅하는 코드
            // document : browser에서 제공하는 내장객체
            // element를 찾아간다(getElement) id를 통해서(byId)
            // element는 object임. 그래서 element의 value를 가지고 와야한다
            let month = document.getElementById("month").value;
            // view_month(month);
            // innerHTML : html 안에 있는 content
            // let content = document.getElementById("calendar").innerHTML;
            // alert(content);
            let calendar = "<table><tr><td colspan='7'>"+month+"월</td></tr>";
            calendar += make_week(["일","월","화","수","목","금","토"]);
            calendar += make_week(["1","2","3","4","5","6","7"]);
            calendar += make_week(["8","9","10","11","12","13","14"]);
            calendar += make_week(["15","16","17","18","19","20","21"]);
            calendar += make_week(["22","23","24","25","26","27","28"]);
            calendar += make_week(["29","30","31","","","",""]);
            calendar += "</table>";
            document.getElementById("calendar").innerHTML = calendar;
        }

       function select_month() {
            let month = document.getElementById("combo").value;
            let result = view_month_auto(month);
            document.getElementById('calendar').innerHTML = result;
       }

