import './home.scss'
import {Button, Form, Input, message, Select, Checkbox, Col, Row} from "antd";
import {connectApi, exportDbTableApi, listDbTableApi} from "../../api/db";
import {useState} from "react";

function Home() {
    const [connectForm] = Form.useForm();
    const [exportForm] = Form.useForm();
    const [getTableBtnDisabled, setGetTableBtnDisabled] = useState(true);
    const [tableList, setTableList] = useState([]);
    const [exportDocumentBtnDisabled, setExportDocumentBtnDisabled] = useState(true);
    const [checkTableName, setCheckTableName] = useState([]);


    /**
     * 判断连接数据库
     */
    const onFinish = () => {
        connectForm.validateFields().then(async res => {
            let resData = await connectApi(res);
            if (resData.code === 1) {
                setGetTableBtnDisabled(false)
                message.success('连接成功')
            }
        }).catch((error) => {
            console.log(error)
        })
    };

    /**
     * 获取数据库表
     * @returns {Promise<void>}
     */
    const listDbTable = async () => {
        let fieldsValue = connectForm.getFieldsValue();
        let reqData = {
            ...fieldsValue,
            'dbName': fieldsValue['url'].split('/')[3]
        }
        let resData = await listDbTableApi(reqData);
        if (resData.code === 1) {
            setTableList(resData.data)
            setExportDocumentBtnDisabled(false)
            message.success('获取成功')
        }
    }

    /**
     * 导出表格
     */
    const exportDbTable = (data) => {
        exportForm.validateFields().then(async res => {
            let connectFormFieldsValue = connectForm.getFieldsValue();
            let exportFormFieldsValue1 = exportForm.getFieldsValue();
            let reqData = {
                ...connectFormFieldsValue,
                ...exportFormFieldsValue1,
                ...data
            }
            let resData = await exportDbTableApi(reqData);
            if (resData.code === 1) {
                message.success('导出成功')
            }
        }).catch((error) => {
            console.log(error)
        })
    }
    return (
        <div id={'home'}>
            <div className={'db-connect-info'}>
                <span className={'title'}>
                    数据库连接信息:
                </span>
                <Form
                    onFinish={onFinish}
                    layout={'inline'}
                    form={connectForm}
                    initialValues={{
                        'type': 'mysql'
                    }}
                ><Form.Item name="type" rules={[
                    {
                        required: true,
                        message: 'Please input your type!',
                    },
                ]} label="数据库类型">
                    <Select
                        style={{
                            width: '10vw',
                        }}
                        options={[
                            {
                                value: 'mysql',
                                label: 'mysql',
                            },
                            {
                                value: 'oracle',
                                label: 'oracle',
                            },
                            {
                                value: 'sqlserver',
                                label: 'sqlserver',
                            }
                        ]}
                    />
                </Form.Item>
                    <Form.Item name='url' rules={[
                        {
                            required: true,
                            message: 'Please input your url!',
                        },
                    ]} label="数据库连接地址">
                        <Input style={{
                            width: '20vw',
                        }} placeholder="例如 Mysql: jdbc:mysql://ip:port/db_name"/>
                    </Form.Item>
                    <Form.Item name='user' rules={[
                        {
                            required: true,
                            message: 'Please input your user!',
                        },
                    ]} label="账号">
                        <Input style={{
                            width: '10vw',
                        }} placeholder="输入数据库连接账号"/>
                    </Form.Item>
                    <Form.Item name='password' rules={[
                        {
                            required: true,
                            message: 'Please input your password!',
                        },
                    ]} label="密码">
                        <Input style={{
                            width: '10vw',
                        }} placeholder="输入数据库连接密码"/>
                    </Form.Item>
                    <Form.Item>
                        <Button type="primary" htmlType="submit">连接测试</Button>
                    </Form.Item>
                    <Form.Item>
                        <Button disabled={getTableBtnDisabled} type="primary"
                                onClick={listDbTable}>获取表格信息</Button>
                    </Form.Item>
                </Form>
            </div>
            <div className={'db-export-check'}>
                <span className={'title'}>
                    数据库导出选择:
                </span>
                <Form
                    onFinish={onFinish}
                    layout={'inline'}
                    form={exportForm}
                    initialValues={{
                        'fileType': 'word'
                    }}
                ><Form.Item name="fileType" rules={[
                    {
                        required: true,
                        message: 'Please input your fileType!',
                    },
                ]} label="导出类型">
                    <Select
                        style={{
                            width: '10vw',
                        }}
                        options={[
                            {
                                value: 'word',
                                label: 'word',
                            },
                            {
                                value: 'html',
                                label: 'html',
                            },
                            {
                                value: 'md',
                                label: 'md',
                            }
                        ]}
                    />
                </Form.Item>
                    <Form.Item name='fileOutputDir' rules={[
                        {
                            required: true,
                            message: 'Please input your fileOutputDir!',
                        },
                    ]} label="文件存储路径">
                        <Input style={{
                            width: '20vw',
                        }} placeholder="输入文件存储路径"/>
                    </Form.Item>
                    <Form.Item name='fileName' rules={[
                        {
                            required: true,
                            message: 'Please input your fileName!',
                        },
                    ]} label="文件名">
                        <Input style={{
                            width: '10vw',
                        }} placeholder="输入文件名"/>
                    </Form.Item>
                    <Form.Item name='description' rules={[
                        {
                            required: true,
                            message: 'Please input your description!',
                        },
                    ]} label="描述">
                        <Input style={{
                            width: '10vw',
                        }} placeholder="输入描述"/>
                    </Form.Item>
                    <Form.Item name='version' rules={[
                        {
                            required: true,
                            message: 'Please input your version!',
                        },
                    ]} label="版本">
                        <Input style={{
                            width: '10vw',
                        }} placeholder="输入版本"/>
                    </Form.Item>
                </Form>
                <div className={'btn-area'}>
                    <Button type={"primary"} disabled={checkTableName.length===0} onClick={() => {
                        exportDbTable({'ignoreTableName': checkTableName.join(","), 'checkTableName': ''})
                    }}>排除选中导出</Button>
                    <Button type={"primary"} disabled={exportDocumentBtnDisabled} onClick={() => {
                        exportDbTable({'ignoreTableName': '', 'checkTableName': ''})
                    }}>全部导出</Button>
                    <Button type={"primary"} disabled={checkTableName.length===0} onClick={() => {
                        exportDbTable({'ignoreTableName': '', 'checkTableName': checkTableName.join(",")})
                    }}>选中导出</Button>
                </div>
            </div>
            <div className={'db-db-info'}>
                <span className={'title'}>表格展示区:</span>
                <div className={'table-area'}>
                    <Checkbox.Group
                        style={{
                            width: '100%',
                        }}
                    >
                        <Row>
                            {
                                tableList.map(item => {
                                    return <Col span={6} key={item.name}>
                                        <Checkbox onChange={(e) => {
                                          let parse = JSON.parse(JSON.stringify(checkTableName));
                                            if(e.target.checked){
                                                parse.push(e.target.value);
                                            }else{
                                                parse.splice(checkTableName.indexOf(e.target.value), 1);
                                            }
                                            setCheckTableName(parse);
                                        }} value={item.name}>{item.name + ':' + item.comment}</Checkbox>
                                    </Col>
                                })
                            }
                        </Row>
                    </Checkbox.Group>
                </div>
            </div>
        </div>
    );
}

export default Home;

