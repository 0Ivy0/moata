const tabButtons = document.querySelectorAll('.tab-btn');
const tabContents = document.querySelectorAll('.tab-content');

tabButtons.forEach(btn => {
    btn.addEventListener('click', () => {
        const target = btn.dataset.tab;

        // 모든 버튼 초기화
        tabButtons.forEach(b => {
            b.classList.remove('border-blue-600', 'border-b-2', 'text-black');
            b.classList.add('text-gray-600');
        });

        // 선택된 버튼 활성화
        btn.classList.add('border-blue-600', 'border-b-2', 'text-black');
        btn.classList.remove('text-gray-600');

        // 탭 내용 전부 숨기기
        tabContents.forEach(c => c.classList.add('hidden'));

        // 클릭된 탭만 보이기
        document.getElementById(`tab-${target}`).classList.remove('hidden');
    });
});

document.querySelector('.tab-btn[data-tab="activity"]').click();
