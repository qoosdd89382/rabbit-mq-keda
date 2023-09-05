import http from 'k6/http';
import { sleep } from 'k6';

const durationSec = 30;

export let options = {
    scenarios: {
        contacts: {
            executor: 'constant-vus',
            vus: 10,
            duration: durationSec + 's',
            gracefulStop: '3s',
        },
    },
};

export default function () {
    http.get('http://producer/send');
    sleep(0.01);
}
