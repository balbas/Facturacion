var data = { menus: [
{ 
  label: 'Facturación',
  href: '#',
  submenus: [
	{
		label: 'Administrador de Facturas',
		href: '..',
		opciones: [
		{
			label: 'Consulta de Facturas',
			href: 'quiter://SCR.CONTA.CRTEDFACQT'
		},
    {
			label: 'Nueva Factura',
			href: 'quiter://SCR.CONTA.CRTEDFACQT'
		}]
	},
	{
		label: 'Administrador de Informes',
		opciones: [
		{
			label: 'Consulta de Informes',
			href: 'quiter://SCR.CONTA.CRTEDEFCQT'
		},
    {
			label: 'Nuevo Informe',
			href: 'quiter://SCR.CONTA.CRTEDEFCQT'
		}]
	},
  {
		label: 'Ficheros Maestros',
		opciones: [
		{
      label: 'Fichero de Clientes',
      href: 'quiter://SCR.CONTA.FMCUEP'
		},
		{
      label: 'Fichero de Empresas',
			href: 'quiter://SCR.CONTA.FMCUCQT'
		}]
	}],
  submenusExt: [
  {
		label: '',
		opciones: [
			{

			}
		]
	},
  {
		label: 'Ejecutar Informes',
		opciones: [
			{
			label: 'Ejecutar Informes',
			href: 'quiter://SCR.CONTA.CRTEDPESPT/?XK=CQT'
			}
		]
	},
  ]
},
{ 
  label: 'Configuración',
  href: '#',
  submenus: [
	{
		label: 'Parámetros de la Aplicación',
		href: '..',
		opciones: [
		{
      label: 'Definir Parámetros',
			href: 'quiter://SCR.CONTA.CRTDG4CG'
		}]
	},
	{
		label: 'Base de Datos',
		opciones: [
    {
      label: 'Parámetros de la Base de Datos',
			href: 'facturacion://jmb.facturacion.frontend.views.ParametersBBDD'
		},
		{
      label: 'Copias de Seguridad',
			href: 'quiter://SCR.CONTA.CRTEDVCOMCG'
		}]
	}]
}]};

