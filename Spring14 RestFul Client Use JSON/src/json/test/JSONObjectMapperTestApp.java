package json.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import netscape.javascript.JSObject;
import spring.domain.User;

public class JSONObjectMapperTestApp {
	public static void main(String[] args) throws Exception {
		User user = new User("user01","홍길동","1111",null,10);
		/*
		 * JSON(CSV) => Java Bean Binding
		 * JavaBean => JSON(CSV) 변환
		 * **/
		
		ObjectMapper objectMapper = new ObjectMapper(); ///jackson.map.ObjectMapper
		System.out.println("\n\n////////////////////////////////////////////");
		System.out.println("1.Domain Object");
		String jsonOneValue = objectMapper.writeValueAsString(user); ///jsonString
		System.out.println(jsonOneValue);
		System.out.println();
		
		///jsonOneValue =>{"userId":"user01","userName":"홍길동","password":"1111","age":null,"grade":10,"regDate":null,"active":false}
		/// readValue 이용해서 변환
		System.out.println("1.JSON Value => Domain Object 변환 및 값 추출"); ///jsonValue(String) -> domain
		User returnUser = objectMapper.readValue(jsonOneValue, User.class); ///readValue
		System.out.println(User.class);
		System.out.println(returnUser);
		System.out.println("userId : "+ returnUser.getUserId());
		System.out.println();
		
		System.out.println("1.JSON Value => JSON Object 사용 및 값 추출"); ///jsonValue(String) -> jsonObject
		///jsonValue => {"userId":"user01","userName":"홍길동","password":"1111","age":null,"grade":10,"regDate":null,"active":false}
		JSONObject jsonObject = (JSONObject)JSONValue.parse(jsonOneValue);
		///JSONValue => 
		System.out.println(jsonObject);
		System.out.println("UserID : " + jsonObject.get("userId"));
		
		System.out.println("\n\n////////////////////////////////////////////");
		List<User> list = new ArrayList<User>(10);
		list.add(user);
		list.add(new User("user02","홍길동","2222",null,20));
		
		System.out.println("2.List<User> => JSON Value(string) 로 변환");
		String jsonManyValue = objectMapper.writeValueAsString(list); ///list도 jsonValue로 변환 writeValueAsString
		System.out.println(jsonManyValue);
		System.out.println();
		

		System.out.println("2.JSON VALUE<String> => List(User) 로 변환 및 값 추출"); ///jasonValue -> domain (list)
		List<User> returnList = objectMapper.readValue(jsonManyValue,new TypeReference<List<User>>() {});

		System.out.println(returnList);
		System.out.println(returnList.get(0));
		System.out.println("userId : " + returnList.get(0).getUserId());
		System.out.println(); ///바뀐 list에서 가져온 값들
		
		System.out.println("2.JSON Value => JSONObject 사용 및 값 추출");///jasonValue -> jason
		JSONArray jsonArray = (JSONArray)JSONValue.parse(jsonManyValue);
		System.out.println(jsonArray);
		System.out.println((JSONObject)jsonArray.get(0));
		System.out.println("userId : "+((JSONObject)jsonArray.get(0)).get("userId"));
		
		System.out.println("\n\n////////////////////////////////////////////");
		Map<String, User> map = new HashMap<String, User>();
		map.put("1",user);
		map.put("2",new User("user02","홍길동","2222",null,20));
		
		System.out.println("3. Map<User> => JSON Value(String) 로 변환");
		jsonManyValue = objectMapper.writeValueAsString(map);
		System.out.println(jsonManyValue);
		System.out.println();
		
		System.out.println("3.JSON Value(String)=>Map<User>로 변환 및 값 추출");
		Map<String,User> returnMap = objectMapper.readValue(jsonManyValue, 
															new TypeReference<Map<String,User>>(){});
		System.out.println(returnMap);
		System.out.println(returnMap.get("1"));
		System.out.println(returnMap.get("1").getUserId());
		System.out.println();
		
		System.out.println("2.JSON Value => JSONObject 사용 및 값 추출");
		jsonObject = (JSONObject)JSONValue.parse(jsonManyValue);
		System.out.println(jsonObject);
		System.out.println((JSONObject)jsonObject.get("1"));
		System.out.println("userId : "+((JSONObject)jsonObject.get("1")).get("userId"));
		
		
		
		
	}
}
