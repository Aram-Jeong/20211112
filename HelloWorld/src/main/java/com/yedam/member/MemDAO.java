package com.yedam.member;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemDAO extends DAO {
	// userData list
	public List<UserVO> getUserList(){
		String sql = "select * from userdata order by 1";
		connect();
		List<UserVO> userList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setEmail(rs.getString("user_email"));
				vo.setPhone(rs.getString("user_phone"));
				vo.setBirth(rs.getString("user_birth"));
				userList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}return userList;
	}
	
	// userData 등록
	public boolean insertUser(UserVO vo) {
		String sql = "insert into userdata(user_id, user_name, user_email, user_phone, user_birth) values(?, ?, ?, ?, ?)";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getEmail());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getBirth());
			// System.out.println(vo.getBirth());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			disconnect();
		}return true;
	}
	
	// datatable 리스트
	public List<DataTable> getDataTables(){
		connect();
		String sql = "select * from data_table";
		List<DataTable> list = new ArrayList<DataTable>();
		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				DataTable dt = new DataTable();
				dt.setName(rs.getString("first_name"));
				dt.setPosition(rs.getString("last_name"));
				dt.setOffice(rs.getString("position"));
				dt.setExtn(rs.getString("office"));
				dt.setStartDate(rs.getString("start_date"));
				dt.setSalary(rs.getString("salary"));
				
				list.add(dt);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}
	
	// datatable 업로드
	public void insertDataTalbe(DataTable dt) {
		connect();
		String sql = "insert into data_table values(?,?,?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dt.getName());
			psmt.setString(2, dt.getPosition());
			psmt.setString(3, dt.getOffice());
			psmt.setString(4, dt.getExtn());
			psmt.setString(5, dt.getStartDate());
			psmt.setString(6, dt.getSalary());
			int r = psmt.executeUpdate();
			System.out.println(r + " insert.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}

	}
	// 상품조회(상품아이디)
		public ItemVO searchItem(String id) {
			connect();
			String sql ="select * from item where prod_id = ?";
			ItemVO vo = null; // 초기화
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setInt(1,Integer.parseInt(id));
				
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					vo = new ItemVO();
					vo.setLikeIt(rs.getDouble("like_it"));
					vo.setOriginPrice(rs.getInt("origin_price"));
					vo.setProdDesc(rs.getString("prod_desc"));
					vo.setProdId(rs.getInt("prod_id"));
					vo.setProdImage(rs.getString("prod_image"));
					vo.setProdItem(rs.getString("prod_item"));
					vo.setSalePrice(rs.getInt("sale_price"));				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				disconnect();
			}
			return vo;
		}

	// 상품 업로드
	public ItemVO uploadProduct(ItemVO vo) {
		connect();
		String sql = "insert into item(prod_id, prod_item, prod_desc, like_it, origin_price, sale_price, prod_image)"
				+ "values(?,?,?,?,?,?,?)";

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select max(prod_id) + 1 from item");
			int nextId = -1;
			if (rs.next()) {
				nextId = rs.getInt(1); // 조회된 값의 첫 번째 컬럼
				vo.setProdId(nextId); // 매개값의 참조변수의 값을 변경
			}
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, nextId);
			psmt.setString(2, vo.getProdItem());
			psmt.setString(3, vo.getProdDesc());
			psmt.setDouble(4, vo.getLikeIt());
			psmt.setInt(5, vo.getOriginPrice());
			psmt.setInt(6, vo.getSalePrice());
			psmt.setString(7, vo.getProdImage());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}

	// 상품 리스트
	public List<ItemVO> getItemList() {
		connect();
		String sql = "select * from item order by 1";
		List<ItemVO> list = new ArrayList<ItemVO>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ItemVO vo = new ItemVO();
				vo.setLikeIt(rs.getDouble("like_it"));
				vo.setOriginPrice(rs.getInt("origin_price"));
				vo.setProdDesc(rs.getString("prod_desc"));
				vo.setProdId(rs.getInt("prod_id"));
				vo.setProdImage(rs.getString("prod_image"));
				vo.setProdItem(rs.getString("prod_item"));
				vo.setSalePrice(rs.getInt("sale_price"));
				System.out.println(vo.toString());
				list.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 스케줄 등록 메소드(title, start, end) <- 매개값
	public boolean addSchedule(String title, String start, String end) {
		connect();
		String sql = "insert into schedule values(?, ?, ?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, title);
			psmt.setString(2, start);
			psmt.setString(3, end);
			int r = psmt.executeUpdate();
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect(); // 성공 유무와 상관없이 무조건 디스커넥트는 실행
		}
		return false;
	}

	// fullCalendar Sample Data
	// List<Map<String, String>>
	public List<Map<String, String>> getSchedules() {
		List<Map<String, String>> schedules = new ArrayList<Map<String, String>>();
		connect();
		String sql = "select * from schedule";
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("title", rs.getString("title"));
				map.put("start", rs.getString("start_date"));
				map.put("end", rs.getString("end_date"));
				schedules.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return schedules;
	}

	// 부서별 인원
	public Map<String, Integer> getMemberByDept() {
		connect();
		String sql = "select 'Administration', 1 from dual\r\n" + "union all\r\n"
				+ "select 'Accounting', 2 from dual\r\n" + "union all\r\n" + "select 'IT', 3 from dual\r\n"
				+ "union all\r\n" + "select 'Executive', 3 from dual\r\n" + "union all\r\n"
				+ "select 'Shipping', 5 from dual\r\n" + "union all\r\n" + "select 'Sales', 3 from dual\r\n"
				+ "union all\r\n" + "select 'Marketing', 2 from dual";
		Map<String, Integer> map = new HashMap<String, Integer>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				map.put(rs.getString(1), rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return map;
	}

	// 페이지별 토탈 건수 가져오기
	public int getTotalCount() {
		connect();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select count(1) from member");
			if (rs.next()) {
				int r = rs.getInt(1); // 가지고 온 컬럼 중 1번
				System.out.println("조회건수: " + r);
				return r;
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			disconnect();
		}
		return 0;

	}

	// 페이지별 데이터 조회
	public List<MemberVO> getMemberByPage(String page) {
		connect();
		List<MemberVO> list = new ArrayList<>();
		String sql = "select b.*from\r\n"//
				+ "(select rownum as num, a.*from\r\n"//
				+ "(select * from member order by 1)a)b\r\n"//
				+ "where b.num >=?\r\n"//
				+ "and b.num <= ?";
		int start = (Integer.parseInt(page) - 1) * 10 + 1;
		int end = (Integer.parseInt(page)) * 10;

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, start);
			psmt.setInt(2, end);

			rs = psmt.executeQuery();
			while (rs.next()) {
				MemberVO mem = new MemberVO();
				mem.setAddress(rs.getString("address"));
				mem.setBirthDate(rs.getString("birth_date"));
				mem.setGender(rs.getString("gender"));
				mem.setPhone(rs.getString("phone"));
				mem.setUserId(rs.getString("user_id"));
				mem.setUserName(rs.getString("user_name"));
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}

	// 조회 기능
	public List<MemberVO> searchMemberList(MemberVO vo) {
		connect();
		List<MemberVO> list = new ArrayList();
		String sql = "select * from member m\r\n" + "where user_id = nvl(?, user_id)\r\n"
				+ "and    nvl(user_name,'1') like '%'||?||'%'\r\n" + "and    nvl(address, '1') like '%'||?||'%'\r\n"
				+ "and    nvl(phone, '1') like '%'||?||'%'";
		if (vo.getGender() != null && !vo.getGender().equals("all") && vo.getGender() != "") {
			sql += "and   gender = ?";
		}
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getPhone());
			if (vo.getGender() != null && !vo.getGender().equals("all") && vo.getGender() != "") {
				psmt.setString(5, vo.getGender());
			}
			rs = psmt.executeQuery();
			while (rs.next()) {
				MemberVO mem = new MemberVO();
				mem.setAddress(rs.getString("address"));
				mem.setBirthDate(rs.getString("birth_date"));
				mem.setGender(rs.getString("gender"));
				mem.setPhone(rs.getString("phone"));
				mem.setUserId(rs.getString("user_id"));
				mem.setUserName(rs.getString("user_name"));
				list.add(mem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;

	}

	// 수정 기능
	public Map<String, Object> updateMember(MemberVO vo) {
		// retCode: OK, retVal: vo
		// retCode: NG, retVal: errMsg
		String sql = "update member ";
		sql += "       set    user_name = ?";
		sql += "            , birth_date = ?";
		sql += "            , gender = ?";
		sql += "            , address = ?";
		sql += "            , phone = ?";
		sql += "       where user_id = ?";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("retCode", "NG");
		map.put("retVal", "Error!");
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserName());
			psmt.setString(2, vo.getBirthDate());
			psmt.setString(3, vo.getGender());
			psmt.setString(4, vo.getAddress());
			psmt.setString(5, vo.getPhone());
			psmt.setString(6, vo.getUserId());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 수정");
			if (r > 0) {
				map.put("retCode", "OK");
				map.put("retVal", vo); // 입력받은 vo 보여주기
				return map; // 한 건 정상처리되면 리턴해줌
			}
		} catch (SQLException e) {
			e.printStackTrace();
			map.put("retVal", e.getMessage());
			return map;
		} finally {
			disconnect();
		}
		return map;

	}

	// 한 건 삭제
	public boolean deleteMember(String id) {
		String sql = "delete from member where user_id = ?"; // 파라메타로 들어오는 값을 받아서 지움
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int r = psmt.executeUpdate(); // 이걸 실행
			System.out.println(r + "건 삭제");
			if (r > 0) { // 지워졌다는 의미
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return false;
	}

	// 한 건 조회
	public MemberVO getMember(String id) {
		String sql = "select * from member where user_id = ?";
		connect();
		MemberVO vo = null;
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if (rs.next()) {
				vo = new MemberVO();
				vo.setAddress(rs.getString("address"));
				vo.setBirthDate(rs.getString("birth_date"));
				vo.setGender(rs.getString("gender"));
				vo.setPhone(rs.getString("phone"));
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null; // 예외 발생
		} finally {
			disconnect();
		}
		return vo;

	}

	// 한 건 입력
	public boolean insertMember(MemberVO vo) {
		String sql = "insert into member(user_id, user_name, address, phone, birth_date, gender) values(?,?,?,?,?,?)";
		connect();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getUserId());
			psmt.setString(2, vo.getUserName());
			psmt.setString(3, vo.getAddress());
			psmt.setString(4, vo.getPhone());
			psmt.setString(5, vo.getBirthDate());
			psmt.setString(6, vo.getGender());
			System.out.println(vo.getBirthDate());
			int r = psmt.executeUpdate();
			System.out.println(r + "건 입력됨.");
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;

	}

	// 전체 리스트
	public List<MemberVO> getMemberList() {
		String sql = "select * from member order by 1";
		connect();
		List<MemberVO> memberList = new ArrayList<>();
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				MemberVO vo = new MemberVO();
				vo.setUserId(rs.getString("user_id"));
				vo.setUserName(rs.getString("user_name"));
				vo.setBirthDate(rs.getString("birth_date"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress(rs.getString("address"));
				vo.setGender(rs.getString("gender"));
				memberList.add(vo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return memberList;
	}

}
