import http from 'k6/http';
import {check} from 'k6';

export const options = {
    scenarios: {
        constant_request_rate: {
            executor: 'constant-arrival-rate',
            rate: 100, // 초당 100개의 요청을 보냅니다.
            timeUnit: '1s', // rate가 기준으로 삼는 시간 단위 (여기서는 1초)
            duration: '30s', // 전체 테스트 기간
            preAllocatedVUs: 50, // 미리 할당된 가상 사용자 수
            maxVUs: 100, // 테스트 중에 사용할 최대 가상 사용자 수
        },
    },
    thresholds: {
        http_req_duration: ['p(95)<500'], // 95%의 요청이 500ms 이내에 완료되어야 합니다.
        'http_req_failed{scenario:constant_request_rate}': ['rate<0.01'], // 실패한 요청 비율이 1% 미만이어야 합니다.
    },
};

export default function () {
    const res = http.get('http://localhost:8080/posts/page/123');

    // 응답 상태 코드가 200인지 확인
    check(res, {
        'status is 200': (r) => r.status === 200,
    });
}
