import http from 'k6/http';
import {check} from 'k6';

export const options = {
    scenarios: {
        vu_100_scenario1: {
            executor: 'per-vu-iterations',
            startTime: '20s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario2: {
            executor: 'per-vu-iterations',
            startTime: '55s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario3: {
            executor: 'per-vu-iterations',
            startTime: '90s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario4: {
            executor: 'per-vu-iterations',
            startTime: '125s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario5: {
            executor: 'per-vu-iterations',
            startTime: '160s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario6: {
            executor: 'per-vu-iterations',
            startTime: '195s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario7: {
            executor: 'per-vu-iterations',
            startTime: '230s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario8: {
            executor: 'per-vu-iterations',
            startTime: '265s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario9: {
            executor: 'per-vu-iterations',
            startTime: '300s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario10: {
            executor: 'per-vu-iterations',
            startTime: '335s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario11: {
            executor: 'per-vu-iterations',
            startTime: '370s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario12: {
            executor: 'per-vu-iterations',
            startTime: '405s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario13: {
            executor: 'per-vu-iterations',
            startTime: '440s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario14: {
            executor: 'per-vu-iterations',
            startTime: '475s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario15: {
            executor: 'per-vu-iterations',
            startTime: '510s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario16: {
            executor: 'per-vu-iterations',
            startTime: '545s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario17: {
            executor: 'per-vu-iterations',
            startTime: '580s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario18: {
            executor: 'per-vu-iterations',
            startTime: '615s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario19: {
            executor: 'per-vu-iterations',
            startTime: '650s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
        vu_100_scenario20: {
            executor: 'per-vu-iterations',
            startTime: '685s',
            gracefulStop: '5s',
            vus: 100,
            iterations: 200,
            maxDuration: '30s',
        },
    },
};

export default function () {
    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    let response = http.get('http://localhost:8080/posts/page/123', params);

    check(response, {
        'is status 200': (r) => r.status === 200,
    });
}
