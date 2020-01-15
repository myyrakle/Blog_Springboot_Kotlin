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
),
(
    now(),
    "[LLVM-IR] 함수 속성 개요 (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

함수 속성은 함수에 관한 추가 정보를 전달할 때 사용됩니다.

함수 속성은 함수의 타입을 나누진 않지만 해당 함수의 일부로 간주됩니다.
그래서 다른 함수 속성을 가진 함수끼리도 함수의 타입은 동일할 수 있어요.

함수 속성은 지정된 형식을 따르는 간단한 키워드입니다.

여러개의 속성이 필요하다면 이렇게 공간을 분할하면 돼요

define void @f() noinline
{ ... }
define void @f() alwaysinline
{ ... }
define void @f() alwaysinline optsize
{ ... }
define void @f() optsize
{ ... }",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: alignstack (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

alignstack(<n>)
이 속성은 프롤로그와 에필로그를 방출할 때, 백엔드가 강제로 스택 포인터를 align하게 합니다.

원하는 alignment를 지정하고 싶다면, 소괄호 안에 2제곱수로 써줘야만 합니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: allocsize (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

allocsize(<EltSizeParam>[, <NumEltsParam>])
이 속성은, 이게 달린 함수가 항상 주어진 바이트 수나 null을 반환할 것임을 나타냅니다.

여기에 들어갈 인자들은 0으로 인덱스된 파라미터 숫자인데요.
하나의 인자라도 제공된다면, 최소한 CallSite로 간주합니다.


Args[EltSizeParam]
반환될 포인터에서 바이트들을 사용할 수 있습니다.
만약 2개가 달릴 경우, CallSite로 가정합니다.


Args[EltSizeParam] * CallSite.Args[NumEltsParam]
바이트들을 사용할 수 있습니다.
참조된 파라미터들은 정수 타입이어야 하요.

반환된 메모리 블럭의 내용은 가정할 수 없습니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: alwaysinline (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

alwaysinline
이 속성은 인라인 처리기가 이 함수를 가능할때마다 호출자 안으로 인라인 시도를 하게 합니다.
해당 호출자의 활성 인라인 크기 한계치 같은걸 전부 무시하고요.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: builtin (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

builtin
이 속성은 호출 사이트(call site)에 있는 피호출자 함수가 nobuilt 속성으로 선언됐더라도 내장(built-in) 함수처럼 인식해야함을 나타냅니다.

이건 호출 사이트에서 nobuiltin 속성이 선언된 함수를 곧장 호출할 때만 유효합니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: cold (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

cold
이 속성은 해당 함수가 드물게 호출될 것임을 표시합니다.
edge 가중치(weight)들을 계산할때, cold 함수 호출에 의해 제어될(post-dominated) 기본 블럭도 cold가 되게끔 해서 낮은 가중치가 주어지게 합니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: speculative_load_hardening (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

speculative_load_hardening
이 속성은 해당 함수 body에서 Speculative Load Hardening를 활성화시킵니다.

Speculative Load Hardening는 제어 흐름의 miss-speculation, 특히 branch의 miss-speculation을 이용한 정보 누출 공격에 대한 최선의 대책입니다.

일반적으로 그런 공격을 가능하게 하는 취약점들은 “Spectre variant #1”로 분류됩니다.

특히, 이건 “Spectre variant #2” 취약점들로 분류된 branch 타겟의 miss-speculation에 대한 완화(mitigate)를 시도하진 않습니다.
->Notably, this does not attempt to mitigate against miss-speculation of branch target, classified as “Spectre variant #2” vulnerabilities.

인라인될때, 이 속성은 끈적거리는(sticky) 성질을 가집니다.
이 속성이 달린 함수를 인라인하면 호출자도 이 속성을 갖게 되거든요.

이건 해당 함수의 코드가 항상(인라인 후에도) 강화되는(hardened) 최고의 보수적(conservative) 모델을 제공하기 위한 겁니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: speculatable (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

speculatable
이 속성은 해당 함수가 그 결과를 계산하는 것 이외의 그 어떤 효과도 갖지 않으며, 정의되지 않은 행동도 없음을 나타냅니다.

speculatable으론 어떤 특정 실행 경로를 따르든지 해당 함수의 호출 수를 외부에서 관찰할 수 없다고 결론짓기엔 불충분합니다.
->Note that speculatable is not enough to conclude that along any particular execution path the number of calls to this function will not be externally observable.

이 속성은 함수와 그 선언에서만 유효합니다. 각각의 호출 사이트는 아니고요.

만약 함수에 speculatable가 잘못 달리고, 실제로 정의되지 않은 행동을 보인다면.
해당 호출 사이트가 죽은 코드라도 정의되지 않은 행동이 관찰될 수 있습니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: stack-probe-size (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

\"stack-probe-size\"
이 속성은 stack probes의 행동을 제어합니다. \"probe-stack\" 속성이나 기타 ABI에서 필요한 stack probe들까지요.

이건 guard 영역의 크기를 정의하는데요.
해당 함수가 guard 영역의 크기보다 더 큰 스택 공간을 사용하려든다면, stack probing sequence가 방출될 것임을 보장합니다.

이 속성은 인자로 정수값을 하나 받습니다. 디폴트 값은 4096입니다.

만약 stack-probe-size 속성을 가진 함수가 다른 stack-probe-size 함수로 인라인된다면, 그 결과 함수는 더 작은 값의 stack-probe-size를 가지게 됩니다.

만약 stack-probe-size 속성을 가진 함수가 stack-probe-size이 없는 함수로 인라인된다면, 그 결과 함수는 피호출자의 stack-probe-size 속성을 가지게 됩니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: safestack (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

safestack
이 속성은 해당 함수에서 SafeStack 보호를 활성화합니다.

만약 safestack 속성을 가진 함수가 safestack이 없거나, ssp/sspstrong/sspreq 속성을 가진 함수로 인라인된다면, 그 결과 함수는 safestack 속성을 가질 겁니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: uwtable (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

uwtable
이 속성은 대상이 되는 ABI가 예외가 전달하지 않는다는걸 보여줄 수 있더라도, 이 함수에 대해 unwind 테이블 엔트리가 생성되어야 함을 나타냅니다.

이건 일반적으로 ELF x86-64의 경우에요. 하지만 일부 컴파일 단위에선 비활성화될 수도 있습니다.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
),
(
    now(),
    "[LLVM-IR] 함수 속성: strictfp (번역)",
    "https://llvm.org/docs/LangRef.html#function-attributes

strictfp
이 속성은 해당 함수가 엄격한(strict) 부동소수점 의미론을 요구하는 스코프에서 호출될 것임을 나타냅니다.

그럼 LLVM은 부동소수점 반올림 모드에 대한 가정이 필요하거나, 이 함수를 호출해서 설정/삭제할 수 있는 부동소수점 상태 플래그의 상태를 수정하는 최적화를 시도하지 않을 겁니다.
->LLVM will not attempt any optimizations that require assumptions about the floating-point rounding mode or that might alter the state of floating-point status flags that might otherwise be set or cleared by calling this function.

LLVM은 trap이 될 수 있는 부동소수점 인스트럭션을 집어넣지 않을 거에요.",

    (select _id from User where username="ADMIN"),
    (select _id from Category where category_name="함수 속성")
);