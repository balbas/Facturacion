var data = { 
    menus: [{ 
        label: 'Facturaci&oacuten',
        href: '#',
        submenus: [{
            label: 'Administrador de Facturas',
            href: '..',
            opciones: [{
                label: 'Consulta de Facturas',
                href: 'facturacion://jmb.facturacion.view.screens.menu.billing.CheckInvoices'
            }, {
                label: 'Nueva Factura',
                href: '#'
            }]
        }, {
            label: 'Administrador de Informes',
            opciones: [{
                label: 'Consulta de Informes',
                href: '#'
            }, {
                label: 'Nuevo Informe',
                href: '#'
            }]
        }, {
            label: 'Ficheros Maestros',
            opciones: [{
                label: 'Fichero de Clientes',
                href: '#'
            }, {
                label: 'Fichero de Empresas',
                href: '#'
            }]
        }], submenusExt: [{
            label: null
            //opciones: [{
            //}]
        }, {
            label: 'Ejecutar Informes',
            opciones: [{
                label: 'Ejecutar Informes',
                href: '#'
            }]
        }]
    }, { 
        label: 'Configuraci&oacuten',
        href: '#',
        submenus: [{
            label: 'Par&aacutemetros de la Aplicaci&oacuten',
            href: '..',
            opciones: [{
                label: 'Definir Par&aacutemetros',
                href: '#'
            }]
        }, {
            label: 'Base de Datos',
            opciones: [{
                label: 'Par&aacutemetros de la Base de Datos',
                href: 'facturacion://jmb.facturacion.view.screens.menu.configuration.ParametersBBDD'
            }, {
                label: 'Copias de Seguridad',
                href: '#'
            }]
        }]
    }]
};