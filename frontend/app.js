const API_URL = 'http://localhost:8080/api/envios';
let enviosGlobal = [];

document.addEventListener('DOMContentLoaded', () => {
    cargarEnvios();
    
    document.getElementById('form-envio').addEventListener('submit', async (e) => {
        e.preventDefault();
        
        const payload = {
            codigoRastreo: document.getElementById('codigoRastreo').value,
            direccionDestino: document.getElementById('direccionDestino').value,
            pesoKg: parseFloat(document.getElementById('pesoKg').value),
            costo: parseFloat(document.getElementById('costo').value),
            vehiculo: { id: parseInt(document.getElementById('vehiculoId').value) },
            conductor: { id: parseInt(document.getElementById('conductorId').value) }
        };

        try {
            const res = await fetch(API_URL, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });
            
            if (res.ok) {
                alert('Envío registrado con éxito');
                document.getElementById('form-envio').reset();
                cargarEnvios();
            } else {
                const err = await res.json();
                alert('Error al registrar: ' + (err.message || res.statusText));
            }
        } catch (error) {
            console.error('Error:', error);
            alert('Error de conexión');
        }
    });
});

async function cargarEnvios() {
    try {
        const res = await fetch(`${API_URL}/optimizados`);
        if (res.ok) {
            enviosGlobal = await res.json();
            renderEnvios(enviosGlobal);
        }
    } catch (error) {
        console.error('Error cargando envíos:', error);
    }
}

function renderEnvios(envios) {
    const grid = document.getElementById('envios-grid');
    grid.innerHTML = '';
    
    envios.forEach(envio => {
        const tarjeta = document.createElement('div');
        tarjeta.className = 'tarjeta-envio';
        
        tarjeta.innerHTML = `
            <h4>${envio.codigoRastreo}</h4>
            <span class="pill-status status-${envio.estadoEnvio}">${envio.estadoEnvio.replace('_', ' ')}</span>
            <p><strong>Destino:</strong> ${envio.direccionDestino}</p>
            <p><strong>Peso:</strong> ${envio.pesoKg} kg</p>
            <p><strong>Costo:</strong> ₡${envio.costo}</p>
            <div class="acciones">
                ${envio.estadoEnvio === 'PENDIENTE' ? `<button onclick="actualizarEstado(${envio.id}, 'EN_TRANSITO')">Marcar en Tránsito</button>` : ''}
                ${envio.estadoEnvio === 'EN_TRANSITO' ? `<button onclick="actualizarEstado(${envio.id}, 'ENTREGADO')">Marcar Entregado</button>` : ''}
            </div>
        `;
        
        grid.appendChild(tarjeta);
    });
}

async function actualizarEstado(id, nuevoEstado) {
    try {
        const res = await fetch(`${API_URL}/${id}/estado`, {
            method: 'PATCH',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ estadoEnvio: nuevoEstado })
        });
        
        if (res.ok) {
            cargarEnvios();
        } else {
            alert('Error actualizando estado');
        }
    } catch (error) {
        console.error('Error:', error);
    }
}

function filtrar(estado) {
    if (estado === 'TODOS') {
        renderEnvios(enviosGlobal);
    } else {
        const filtrados = enviosGlobal.filter(e => e.estadoEnvio === estado);
        renderEnvios(filtrados);
    }
}
