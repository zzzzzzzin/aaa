package Day1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class LibraryDAO {
	//대출장부 추가
	public boolean insertRentalBook(ArrayList<LoanVO> list) throws SQLException {
		boolean flag = false;
		Connection con = ConnectionManager.getConnection();
		String sql = "insert into loanTBL(loan_date,exp_return_date,std_no,book_no,return_date,retrun_yn) values (?,?,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		int affectedCount = 0;
		for(LoanVO vo : list) {
			pstmt.setString(2, vo.getLoan_date());
			pstmt.setString(3, vo.getExp_return_date());
			pstmt.setInt(4, vo.getStd_no());
			pstmt.setInt(5, vo.getBook_no());
			pstmt.setString(6, vo.getReturn_date());
			pstmt.setString(7, vo.getReturn_yn());
			affectedCount = pstmt.executeUpdate();
		}
		if (affectedCount>0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, con);
		System.out.println("대출장부 추가");
		return flag;
	}
	//예약장부 추가
	public boolean insertReserveBook(ArrayList<ReservationVO> list) throws SQLException {
		boolean flag = false;
		Connection con = ConnectionManager.getConnection();
		String sql = "insert into rsvsTBL(rsvs_date,std_no,book_no) values (?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		int affectedCount = 0;
		for(ReservationVO vo : list) {
			pstmt.setString(2, vo.getRsvs_date());
			pstmt.setInt(3, vo.getStd_no());
			pstmt.setInt(4, vo.getBook_no());
			affectedCount = pstmt.executeUpdate();
		}
		if (affectedCount>0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, pstmt, con);
		System.out.println("예약장부 추가");
		return flag;
	}
	//대출장부 전체 조회
	public ArrayList<LoanVO> selectRentalBook() throws SQLException {
		ArrayList<LoanVO> list = new ArrayList<LoanVO>();
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from loanTBL";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		LoanVO vo = null;
		while (rs.next()) {
			vo = new LoanVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
					rs.getInt(5),rs.getString(6),rs.getString(7));
			list.add(vo);
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return list;
	}
	//학번으로 대출장부 조회
	public ArrayList<LoanVO> selectRentalBook(int std_no) throws SQLException {
		ArrayList<LoanVO> list = new ArrayList<LoanVO>();
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from loanTBL where std_no=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, std_no);
		ResultSet rs = pstmt.executeQuery();
		LoanVO vo = null;
		while (rs.next()) {
			vo = new LoanVO(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),
					rs.getInt(5),rs.getString(6),rs.getString(7));
			list.add(vo);
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return list;
	}
	//예약장부 조회
	public ArrayList<ReservationVO> selectReserveBook() throws SQLException {
		ArrayList<ReservationVO> list = new ArrayList<ReservationVO>();
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from rsvsTBL";
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		ResultSet rs = pstmt.executeQuery();
		ReservationVO vo = null;
		while (rs.next()) {
			vo = new ReservationVO(rs.getInt(1),rs.getString(2),rs.getInt(3),
					rs.getInt(4));
			list.add(vo);
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return list;
	}
	//학생정보 조회
	public ArrayList<StudentVO> selectStudent() throws SQLException {
		ArrayList<StudentVO> list = new ArrayList<StudentVO>();
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from studentTBL";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		StudentVO vo = null;
		while (rs.next()) {
			vo = new StudentVO(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),
					rs.getInt(5),rs.getInt(6),rs.getString(7));
			list.add(vo);
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return list;
	}
	//학번으로 학생정보 조회
	public StudentVO selectStudent(int std_no) throws SQLException {
		StudentVO vo = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from studentTBL where std_no = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, std_no);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			vo = new StudentVO(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getInt(4),
					rs.getInt(5),rs.getInt(6),rs.getString(7));
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return vo;
	}
	//도서정보 조회
	public ArrayList<BookVO> selectBook() throws SQLException {
		ArrayList<BookVO> list = new ArrayList<BookVO>();
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from bookTBL";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();
		BookVO vo = null;
		while (rs.next()) {
			vo = new BookVO(rs.getInt(1),rs.getString(2),rs.getString(3),
					rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7));
			list.add(vo);
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return list;
	}
	//책 분류로 도서정보 조회
	public BookVO selectBook(int book_no) throws SQLException {
		BookVO vo = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select * from bookTBL where book_no = ?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, book_no);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			vo = new BookVO(rs.getInt(1),rs.getString(2),rs.getString(3),
					rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7));
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return vo;
	}
	//대출정보 갱신
	public ArrayList<LoanVO> updateRentalBook() throws SQLException {
		ArrayList<LoanVO> list = new ArrayList<LoanVO>();
		Connection con = ConnectionManager.getConnection();
		String sql = "update loanTBL set *=* where *=* ";
		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, ?);
		
		ResultSet rs = pstmt.executeQuery();
		LoanVO vo = null;
		while (rs.next()) {
			vo = new LoanVO(rs.getString(0));
			
			list.add(vo);
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return list;
	}
	//대여가능여부 갱신
	public boolean updateRentalBook(String loan_yn, int book_no) throws SQLException {
		boolean flag = false;
		Connection con = ConnectionManager.getConnection();
		Statement stmt = null;
		String sql = "update bookTBL set loan_yn='"+loan_yn+"' where book_no="+ book_no;
		stmt = con.createStatement();
		int affectedCount = stmt.executeUpdate(sql);
		if (affectedCount>0) {
			flag = true;
		}
		ConnectionManager.closeConnection(null, stmt, con);
		return flag;
	}
	///////////이용현황////////////
	//대출도서 상위 5위에 대한 정보
	public LoanVO selectRentalBook5() throws SQLException {
		LoanVO vo = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select count(book_name),book_name from loanTBL order by count(book_name) group by book_name limit 5";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			vo = new LoanVO(rs.getString(2));
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return vo;
	}
	//대출자 상위 5위에 대한 정보
	public StudentVO selectStudent5() throws SQLException {
		StudentVO vo = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select sum(rsrv_cnt), std_name from studentTBL group by name limit 5";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			vo = new StudentVO(rs.getString(2));
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return vo;
	}
	//대출기간이 가장 짧은 도서에 대한 정보
	public LoanVO selectBookMinRentalPeriod() throws SQLException {
		LoanVO vo = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select loan.TBL(datediff((cast(return_date as datetime),'%Y%m%d'),(cast(loan_date as datetime),'%Y%m%d'))) as min_return_date, "
					+ "	loanTBL.book_no, "
					+ " bookTBL.book_no, "
					+ " bookTBL.book_name "
					+ " from loanTBL  "
					+ " inner join bookTBL "
					+ "	on loanTBL.book_no = bookTBL.book_no "
					+ " order by loanTBL.min_return_date "
					+ " limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			vo = new LoanVO(rs.getString(4));
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return vo;
	}
	//대출반납이 가장 빠른 학생에 대한 정보
	public StudentVO selectStudentMinReturnPeriod() throws SQLException {
		StudentVO vo = null;
		Connection con = ConnectionManager.getConnection();
		String sql = "select loan.TBL(datediff((cast(return_date as datetime),'%Y%m%d'),(cast(loan_date as datetime),'%Y%m%d'))) as min_return_date, "
					+ "	loanTBL.std_no, "
					+ " studentTBL.std_no, "
					+ " studentTBL.std_name "
					+ " from loanTBL "
					+ " inner join studentTBL "
					+ "	on loanTBL.std_no = studentTBL.std_name "
					+ " order by loanTBL.min_return_date "
					+ " limit 1";
		PreparedStatement pstmt = con.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			vo = new StudentVO(rs.getString(4));
		}
		ConnectionManager.closeConnection(rs, pstmt, con);
		return vo;
	}
	//대출을 가장 많이하는 학과에 대한 정보
	public StudentVO selectStudentMaxRentalMajor() throws SQLException {
		StudentVO vo = null;
	
		
		return vo;
	}
//	//예약정보 갱신
//	public ArrayList<ReservationVO> updateReserveBook() {
//		ArrayList<ReservationVO> list = new ArrayList<ReservationVO>();
//		Connection con = ConnectionManager.getConnection();
//		String sql = "update DB명 set *=* where *=* ";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, ?);
//		
//		ResultSet rs = pstmt.executeQuery();
//		ReservationVO vo = null;
//		while (rs.next()) {
//			vo = new ReservationVO(rs.getString(0));
//			
//			list.add(vo);
//		}
//		ConnectionManager.closeConnection(rs, pstmt, con);
//		return list;
//	}
//	//도서정보 갱신
//	public ArrayList<BookVO> updateBook() {
//		ArrayList<BookVO> list = new ArrayList<BookVO>();
//		Connection con = ConnectionManager.getConnection();
//		String sql = "update DB명 set *=* where *=* ";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		pstmt.setString(1, ?);
//		
//		ResultSet rs = pstmt.executeQuery();
//		BookVO vo = null;
//		while (rs.next()) {
//			vo = new BookVO(rs.getString(0));
//			
//			list.add(vo);
//		}
//		ConnectionManager.closeConnection(rs, pstmt, con);
//		return list;
//	}
	//도서정보추가 - test
//	public boolean insertBook(ArrayList<BookVO> list) throws SQLException {
//		boolean flag = false;
//		Connection con = ConnectionManager.getConnection();
//		String sql = "insert into BookTBL(book_no,book_name,writer,price,pay_date,rsrv_people,loan_yn) values (?,?,?,?,?,?,?)";
//		PreparedStatement pstmt = con.prepareStatement(sql);
//		
//		int affectedCount = 0;
//		for(BookVO vo : list) {
//			pstmt.setInt(1, vo.getBook_no());
//			pstmt.setString(2, vo.getBook_name());
//			pstmt.setString(3, vo.getWriter());
//			pstmt.setInt(4, vo.getPrice());
//			pstmt.setString(5, vo.getPay_date());
//			pstmt.setInt(6, vo.getRsrv_people());
//			pstmt.setString(7, vo.getLoan_yn());
//			affectedCount = pstmt.executeUpdate();
//		}
//		if (affectedCount>0) {
//			flag = true;
//		}
//		ConnectionManager.closeConnection(null, pstmt, con);
//		System.out.println("도서정보 추가");
//		return flag;
//	}
}
