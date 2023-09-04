import http from 'k6/http';
import { sleep } from 'k6';

const durationSec = 30;

export let options = {
    vus: 10,
    duration: durationSec + 's',
};

export default function () {
    const endTime = new Date().getTime() + (durationSec * 1000);
    while (new Date().getTime() < endTime) {
        http.get('http://localhost:8881/send');
        sleep(0.01);
    }
}
