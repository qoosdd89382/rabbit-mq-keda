import http from 'k6/http';
import { sleep } from 'k6';

const durationSec = 5;

export let options = {
    vus: 10,
    duration: durationSec + 's',
};

export default function () {
    const endTime = new Date().getTime() + (durationSec * 1000); // 5秒后结束
    while (new Date().getTime() < endTime) {
        http.get('http://localhost:8881/send');
        sleep(0.01);
    }
}
