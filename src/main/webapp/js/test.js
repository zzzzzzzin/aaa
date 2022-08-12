/**
 * 
 */
 //=== : value + type까지 비교
 //== : 단순비교
 //주어진 수에 대하여 소수 여부를 판별하여 리턴하는 코드 작성
	function prime_number(number) {
		let result="소수가 아닙니다.";
		let count=0;
		for (let i=0;i<number;i++) {
			if (number%(i+1) === 0) {
				count=count+1;
			}
		}
		if (count === 2) {
			result = "소수입니다.";
		}
		return result;
	}

	
	//년도를 입력받아 윤년을 체크한 후 결과를 리턴하는 코드 작성
	function is_check_year(year) {
		let result = false;
		if(year%4===0 && year%100!==0||year%400===0){
			result=true;
		}
		return result;
	}
	
	
	//8월 달력을 콘솔에 출력하는 코드 작성(함수를 작성해서)
	function view_month() {
		console.log("\t\t2022년 08월");
		console.log("일\t월\t화\t수\t목\t금\t토")
		let space=1;
		let day=1;
		let week="";
		for (let i=0;i<space;i++){
			week=week+"\t";
		}
		for(let i=0;i<31;i++) {
			week=week+day+"\t";
			if((space+day)%7===0){
				console.log(week);
				week="";
			}
			day=day+1;
		}
		console.log(week)
	}
	