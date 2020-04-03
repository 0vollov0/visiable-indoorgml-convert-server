# visiable-indoorgml-convert-server
 
## 실행

* Java version 11 이상 및 환경 변수 셋팅

* Gradle 설치 https://gradle.org/releases/  환경 변수 셋팅

* Gradle bootrun command 실행

## 포트

9000

## 설명

IndoorGML 파일과 파일에 해당하는 대표적인 실 좌표 4개를 받아와 IndoorGML 파일의 벡터 좌표를 실 좌표로 변환시켜줍니다.

EPSG:3857 형태의 polygon data 와 EPSG:4326 로 변환된 IndoorGML 파일을 반환합니다.
