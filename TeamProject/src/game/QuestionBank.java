package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionBank {
    private List<Question> questions;

    public QuestionBank() {
        questions = new ArrayList<>();
        loadQuestions();
    }

    private void loadQuestions() {
        questions.add(new Question("보통 'DNA'의 구조는 어떤 형태로 묘사되나요?", new String[]{"나선형", "원형", "사각형", "이중나선형"}, "이중나선형"));
        questions.add(new Question("태양계에서 가장 큰 행성은 무엇인가요?", new String[]{"지구", "목성", "토성", "천왕성"}, "목성"));
        questions.add(new Question("피타고라스 정리는 어떤 도형과 관련이 있나요?", new String[]{"직각삼각형", "정사각형", "원", "직사각형"}, "직각삼각형"));
        questions.add(new Question("세계에서 가장 긴 강은 무엇인가요?", new String[]{"아마존강", "나일강", "양쯔강", "미시시피강"}, "나일강"));
        questions.add(new Question("한글을 창제한 왕은 누구인가요?", new String[]{"태조", "태종", "세종", "문종"}, "세종"));
        questions.add(new Question("컴퓨터의 CPU는 무엇의 약자인가요?", new String[]{"Compact Power Unit", "Central Performance Unit", "Computer Personal Unit", "Central Processing Unit"}, "Central Processing Unit"));
        questions.add(new Question("일반적으로 커피에 포함된 카페인은 어떤 효과가 있나요?", new String[]{"각성", "수면 유도", "소화 촉진", "체온 감소"}, "각성"));
        questions.add(new Question("로마 숫자에서 'X'는 어떤 숫자를 나타내나요?", new String[]{"5", "10", "20", "100"}, "10"));
        questions.add(new Question("'DNS'의 컴퓨터 용어로서의 의미는 무엇인가요?", new String[]{"Digital Network Service", "Data Number Sequence", "Domain Name System", "Dynamic Net Speed"}, "Domain Name System"));
        questions.add(new Question("인체에서 가장 큰 기관은 무엇인가요?", new String[]{"간", "폐", "심장", "피부"}, "피부"));
        questions.add(new Question("비타민 C가 풍부한 과일은 무엇인가요?", new String[]{"오렌지", "바나나", "복숭아", "포도"}, "오렌지"));
        questions.add(new Question("'이순신' 장군이 활약한 전쟁은 무엇인가요?", new String[]{"정묘호란", "임진왜란", "병자호란", "청일전쟁"}, "임진왜란"));
        questions.add(new Question("'빅벤'이라고 불리는 시계탑이 있는 나라는 어디인가요?", new String[]{"프랑스", "독일", "영국", "이탈리아"}, "영국"));
        questions.add(new Question("셰익스피어의 대표작 중 하나는 무엇인가요?", new String[]{"노인과 바다", "파우스트", "데미안", "햄릿"}, "햄릿"));
        questions.add(new Question("세계 최초의 목판 인쇄술은 어디에서 발명되었나요?", new String[]{"대한민국", "이집트", "중국", "인도"}, "대한민국"));
        questions.add(new Question("태평양 전쟁은 언제 일어났나요?", new String[]{"1914-1918년", "1923-1927년", "1937-1941년", "1941-1945년"}, "1941-1945년"));
        questions.add(new Question("월드와이드웹(www)을 처음 만든 사람은 누구인가요?", new String[]{"빌 게이츠", "팀 버너스 리", "윌리엄 게이츠", "마크 주커버그"}, "팀 버너스 리"));
        questions.add(new Question("'개선문'이 위치한 도시는 어디인가요?", new String[]{"런던", "베를린", "파리", "로마"}, "파리"));
        questions.add(new Question("이탈리아의 '피사의 사탑'은 원래 어떤 목적으로 건설되었나요?", new String[]{"감시탑", "기념탐", "성문", "종탑"}, "종탑"));
        questions.add(new Question("'노르망디 상륙 작전'이 일어난 전쟁은 무엇인가요?", new String[]{"제1차 세계대전", "제2차 세계대전", "한국전쟁", "베트남전"}, "제2차 세계대전"));
        questions.add(new Question("바람이 지구의 자전에 의해 휘어지는 현상을 무엇이라고 하나요?", new String[]{"베르누이 효과", "파스칼 법칙", "코리올리 효과", "뉴턴 제3법칙"}, "코리올리 효과"));
        questions.add(new Question("'차르'는 어느 나라의 전통적인 군주 칭호였나요?", new String[]{"프랑스", "영국", "독일", "러시아"}, "러시아"));
        questions.add(new Question("대기 중에서 가장 풍부한 가스는 무엇인가요?", new String[]{"질소", "산소", "이산화탄소", "아르곤"}, "질소"));
        questions.add(new Question("2020년 도쿄 올림픽이 열린 연도는 언제인가요?", new String[]{"2019년", "2020년", "2021년", "2022년"}, "2021년"));
        questions.add(new Question("'아마존 강'이 흐르는 대륙은 어디인가요?", new String[]{"북아메리카", "남아메리카", "아프리카", "아시아"}, "남아메리카"));
        questions.add(new Question("'에베레스트산'이 위치한 산맥은 무엇인가요?", new String[]{"알프스", "록키", "히말라야", "안데스"}, "히말라야"));
        questions.add(new Question("'로마 숫자'에서 50을 나타내는 기호는 무엇인가요?", new String[]{"C", "D", "X", "L"}, "L"));
        questions.add(new Question("'타이타닉' 호가 침몰한 해는 언제인가요?", new String[]{"1912년", "1915년", "1918년", "1920년"}, "1912년"));
        questions.add(new Question("올림픽에서 금메달은 주로 어떤 금속으로 만들어지나요?", new String[]{"금", "은", "동", "플래티넘"}, "은"));
        questions.add(new Question("'마하' 단위는 무엇을 측정하는 데 사용되나요?", new String[]{"압력", "온도", "속도", "높이"}, "속도"));

        
    }

    public List<Question> getRandomQuestions(int numberOfQuestions) {
        Collections.shuffle(questions);
        return questions.subList(0, Math.min(numberOfQuestions, questions.size()));
    }
}
