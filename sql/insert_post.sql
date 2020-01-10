insert into Post(time, title, body, writer_id, category_id)
values(
    now(),
    "[LLVM-IR] 개요(번역)",
    "https://llvm.org/docs/LangRef.html#abstract

    이 문서는 llvm 어셈블리 언어를 위한 레퍼런스 메뉴얼입니다.

llvm은 정적 Single Assignment (SSA) 기반의 표현으로, 타입-safety, 저수준 연산, 유연성 및 고수준 언어의 모든 표현을 제공합니다.

그리고 llvm 컴파일 전략의 모든 단계에서 사용되는 공통된 코드 표현이기도 합니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="시작")
),
(
    now(),
    "[LLVM-IR] 바람직한 형식(Well-Formedness) (번역)",
    "https://llvm.org/docs/LangRef.html#well-formedness

중요한건 이 문서가 ''바람직한 형식(well-formed)''의 llvm 어셈블리 언어를 설명한다는 겁니다.

컴파일러의 파서가 인식하는 것과 ''well formed''한 것에는 분명한 차이가 있습니다.
예를 들어 아래 문장은 문법적으로 옳지만, ''well formed''한 형태는 아닙니다.

%x = add i32 1, %x

이는 %x의 정의가 %x의 사용을 전부 지배하는건 아니기 때문입니다.
(because the definition of %x does not dominate all of its uses.)
(이해 못함)

llvm 인프라는 llvm 모듈이 ''well formed''한지 검증할 수 있게 해주는 검증용 pass를 제공해주는데요.
이 pass는 parser가 입력한 어셈블리를 파싱하기 전과, optimizer가 bitcode를 출력하기 전에, 자동으로 실행됩니다.
그리고 이 pass가 지적한 위반사항은 변환 pass나 파서 입력에서 일어난 버그를 나타냅니다.",
    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="시작")
);