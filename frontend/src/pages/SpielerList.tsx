import React, { useEffect, useState, useCallback } from 'react';
import {
    Box,
    Button,
    Table,
    Thead,
    Tbody,
    Tr,
    Th,
    Td,
    Heading,
    HStack,
    useToast,
    Badge,
    Spinner,
    Center,
    Text,
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalBody,
    ModalCloseButton,
    useDisclosure,
    AlertDialog,
    AlertDialogBody,
    AlertDialogFooter,
    AlertDialogHeader,
    AlertDialogContent,
    AlertDialogOverlay,
} from '@chakra-ui/react';
import { AddIcon, DeleteIcon, EditIcon } from '@chakra-ui/icons';
import { spielerApi } from '../services/api';
import { Spieler } from '../types';
import SpielerForm from '../components/SpielerForm';

const SpielerList: React.FC = () => {
    const [spieler, setSpieler] = useState<Spieler[]>([]);
    const [isLoading, setIsLoading] = useState(true);
    const [selectedSpieler, setSelectedSpieler] = useState<Spieler | null>(null);
    const [deleteId, setDeleteId] = useState<number | null>(null);
    const toast = useToast();
    const { isOpen: isFormOpen, onOpen: onFormOpen, onClose: onFormClose } = useDisclosure();
    const { isOpen: isDeleteOpen, onOpen: onDeleteOpen, onClose: onDeleteClose } = useDisclosure();
    const cancelRef = React.useRef<HTMLButtonElement>(null);

    const loadSpieler = useCallback(async () => {
        console.log('loadSpieler called');
        try {
            const response = await spielerApi.getAll();
            setSpieler(response.data);
        } catch (error) {
            toast({
                title: 'Error',
                description: 'Fehler beim Laden der Spieler',
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
        } finally {
            setIsLoading(false);
        }
    }, [toast]);

    const handleSubmit = async (data: Omit<Spieler, 'id'>) => {
        console.log('handleSubmit called with data:', data);
        try {
            if (selectedSpieler) {
                await spielerApi.update(selectedSpieler.id, data);
                toast({
                    title: 'Erfolg',
                    description: 'Spieler wurde aktualisiert',
                    status: 'success',
                    duration: 3000,
                    isClosable: true,
                });
            } else {
                await spielerApi.create(data);
                toast({
                    title: 'Erfolg',
                    description: 'Spieler wurde erstellt',
                    status: 'success',
                    duration: 3000,
                    isClosable: true,
                });
            }
            await loadSpieler();
            onFormClose();
        } catch (error) {
            toast({
                title: 'Error',
                description: `Fehler beim ${selectedSpieler ? 'Aktualisieren' : 'Erstellen'} des Spielers`,
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
        }
    };

    const handleDelete = async () => {
        console.log('handleDelete called for ID:', deleteId);
        if (!deleteId) return;
        try {
            await spielerApi.delete(deleteId);
            toast({
                title: 'Erfolg',
                description: 'Spieler wurde gelöscht',
                status: 'success',
                duration: 3000,
                isClosable: true,
            });
            await loadSpieler();
        } catch (error) {
            toast({
                title: 'Error',
                description: 'Fehler beim Löschen des Spielers',
                status: 'error',
                duration: 5000,
                isClosable: true,
            });
        } finally {
            onDeleteClose();
            setDeleteId(null);
        }
    };

    const handleEdit = (spieler: Spieler) => {
        console.log('handleEdit called for spieler:', spieler);
        setSelectedSpieler(spieler);
        onFormOpen();
    };

    const handleAdd = () => {
        console.log('handleAdd called');
        setSelectedSpieler(null);
        onFormOpen();
    };

    const handleDeleteClick = (id: number) => {
        console.log('handleDeleteClick called for ID:', id);
        setDeleteId(id);
        onDeleteOpen();
    };

    useEffect(() => {
        loadSpieler();
    }, [loadSpieler]);

    const getPositionColor = (position: string) => {
        switch (position) {
            case 'Torwart':
                return 'yellow';
            case 'Verteidiger':
                return 'blue';
            case 'Mittelfeldspieler':
                return 'green';
            case 'Stuermer':
                return 'red';
            default:
                return 'gray';
        }
    };

    if (isLoading) {
        return (
            <Center h="200px">
                <Spinner
                    thickness="4px"
                    speed="0.65s"
                    emptyColor="gray.200"
                    color="blue.500"
                    size="xl"
                />
            </Center>
        );
    }

    if (spieler.length === 0) {
        return (
            <Box>
                <HStack justify="space-between" mb={5}>
                    <Heading size="lg">Spieler</Heading>
                    <Button leftIcon={<AddIcon />} colorScheme="blue" onClick={handleAdd}>
                        Neuer Spieler
                    </Button>
                </HStack>
                <Center h="200px">
                    <Text>Keine Spieler gefunden</Text>
                </Center>
            </Box>
        );
    }

    return (
        <Box>
            <HStack justify="space-between" mb={5}>
                <Heading size="lg">Spieler</Heading>
                <Button leftIcon={<AddIcon />} colorScheme="blue" onClick={handleAdd}>
                    Neuer Spieler
                </Button>
            </HStack>

            <Table variant="simple">
                <Thead>
                    <Tr>
                        <Th>Name</Th>
                        <Th>Position</Th>
                        <Th>Alter</Th>
                        <Th>Trikotnummer</Th>
                        <Th>Gehalt</Th>
                        <Th>Aktionen</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {spieler.map((s) => (
                        <Tr key={s.id}>
                            <Td>{s.name}</Td>
                            <Td>
                                <Badge colorScheme={getPositionColor(s.position)}>
                                    {s.position}
                                </Badge>
                            </Td>
                            <Td>{s.alter}</Td>
                            <Td>{s.trikotnummer}</Td>
                            <Td>{s.gehalt.toLocaleString('de-DE')} €</Td>
                            <Td>
                                <HStack spacing={2}>
                                    <Button
                                        size="sm"
                                        colorScheme="blue"
                                        leftIcon={<EditIcon />}
                                        onClick={() => handleEdit(s)}
                                    >
                                        Bearbeiten
                                    </Button>
                                    <Button
                                        size="sm"
                                        colorScheme="red"
                                        leftIcon={<DeleteIcon />}
                                        onClick={() => handleDeleteClick(s.id)}
                                    >
                                        Löschen
                                    </Button>
                                </HStack>
                            </Td>
                        </Tr>
                    ))}
                </Tbody>
            </Table>

            {/* Create/Edit Modal */}
            <Modal 
                isOpen={isFormOpen} 
                onClose={onFormClose} 
                size="xl"
                closeOnOverlayClick={false}
                isCentered
            >
                <ModalOverlay />
                <ModalContent>
                    <ModalHeader>
                        {selectedSpieler ? 'Spieler bearbeiten' : 'Neuer Spieler'}
                    </ModalHeader>
                    <ModalCloseButton />
                    <ModalBody pb={6}>
                        <SpielerForm
                            spieler={selectedSpieler || undefined}
                            onSubmit={handleSubmit}
                            onCancel={onFormClose}
                        />
                    </ModalBody>
                </ModalContent>
            </Modal>

            {/* Delete Confirmation Dialog */}
            <AlertDialog
                isOpen={isDeleteOpen}
                leastDestructiveRef={cancelRef}
                onClose={onDeleteClose}
            >
                <AlertDialogOverlay>
                    <AlertDialogContent>
                        <AlertDialogHeader fontSize="lg" fontWeight="bold">
                            Spieler löschen
                        </AlertDialogHeader>

                        <AlertDialogBody>
                            Sind Sie sicher? Diese Aktion kann nicht rückgängig gemacht werden.
                        </AlertDialogBody>

                        <AlertDialogFooter>
                            <Button ref={cancelRef} onClick={onDeleteClose}>
                                Abbrechen
                            </Button>
                            <Button colorScheme="red" onClick={handleDelete} ml={3}>
                                Löschen
                            </Button>
                        </AlertDialogFooter>
                    </AlertDialogContent>
                </AlertDialogOverlay>
            </AlertDialog>
        </Box>
    );
};

export default SpielerList;
